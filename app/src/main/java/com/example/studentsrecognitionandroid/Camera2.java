package com.example.studentsrecognitionandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

public class Camera2 extends AppCompatActivity {

    private Bitmap bmp;
    private TextView textView;
    private String name;
    private int id;
    private SharedPreferences sharedPreferences;

    private Student student;
    private Booking booking;
    private JsonPlaceHolder jsonPlaceHolder;
    private ImageView mimageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private int unit_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        sharedPreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        unit_id = sharedPreferences.getInt("unit_id",0);


        Retrofit retrofit = new Retrofit.Builder()
                //this is for local host when url is 127.0.0.0
//                .baseUrl("http://10.0.2.2:8000/api/")
                .baseUrl("http://192.168.0.17:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        mimageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.student_name);
        textView.setText(String.valueOf(unit_id));


        mimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmStudent();
            }
        });

    }

    private void getStudent(String imagestring) {
        Call<Student> call = jsonPlaceHolder.getStudent(imagestring);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Log.d("trial","=====================================================");
                student = response.body();
                name = student.getReg_no();
                id = student.getId();

                Log.d("trial","====================================================="+student.getImage());
                textView.setText(name+id);

                String url = "http://192.168.0.17:8000"+student.getImage();
                new DownloadImageTask(Camera2.this, mimageView, url).execute();

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.d("=====================", String.valueOf(t));

            }
        });


    }
    public void markAttended(int booking_id){
        Call<Booking> call = jsonPlaceHolder.markAttended(booking_id);

        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {

            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {

            }

        });

        Toast.makeText(Camera2.this,"confirmed student ",Toast.LENGTH_SHORT).show();

    }

    public void confirmStudent(){

        Call<Booking> call = jsonPlaceHolder.findBooking(unit_id,id);

        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                booking = response.body();
                int bookin_id = booking.getId();

                markAttended(bookin_id);




            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {

            }
        });


//        Toast.makeText(Camera2.this,"You can do sth on  "+unit_id + id ,Toast.LENGTH_SHORT).show();






    }

    public void takePicture(View view) {

        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (imageTakeIntent.resolveActivity(getPackageManager()) !=null){

            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            String imb64 = convert(imageBitmap);

            getStudent(imb64);


        }

    }

    // convert to base64
    public String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

}