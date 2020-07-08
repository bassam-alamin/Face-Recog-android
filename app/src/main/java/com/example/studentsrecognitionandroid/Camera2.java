package com.example.studentsrecognitionandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private Student student;
    private JsonPlaceHolder jsonPlaceHolder;
    private ImageView mimageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);

        Retrofit retrofit = new Retrofit.Builder()
                //this is for local host when url is 127.0.0.0
//                .baseUrl("http://10.0.2.2:8000/api/")
                .baseUrl("http://192.168.0.17:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);


        mimageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.student_name);


    }

    private void getStudent(String imagestring) {
        Call<Student> call = jsonPlaceHolder.getStudent(imagestring);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                Log.d("trial","=====================================================");
                student = response.body();
                name = student.getReg_no();
                Log.d("trial","====================================================="+name);
                textView.setText(name);




//                URL url = null;
//                try {
//                    url = new URL("http://192.168.0.17:8000/media/image.jpg");
//                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.d("=====================", String.valueOf(t));

            }
        });



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




//            Toast.makeText(Camera2.this,imb64,Toast.LENGTH_SHORT).show();
            mimageView.setImageBitmap(imageBitmap);

        }

    }

    // convert to base64
    public String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

}