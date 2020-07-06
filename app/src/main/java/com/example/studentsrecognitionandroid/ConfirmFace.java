package com.example.studentsrecognitionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ConfirmFace extends AppCompatActivity {
    private ImageView imgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_face);

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        imgview = findViewById(R.id.imageView);

        // convert to base64

        String imb64 = convert(myBitmap);

        Toast.makeText(ConfirmFace.this,imb64,Toast.LENGTH_SHORT).show();


        imgview.setImageBitmap(myBitmap);



    }
    // convert to base64
    public String convert(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
}