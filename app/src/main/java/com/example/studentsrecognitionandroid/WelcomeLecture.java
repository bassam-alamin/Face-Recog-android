package com.example.studentsrecognitionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeLecture extends AppCompatActivity {
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_lecture);


        Intent intent = getIntent();

        String value = intent.getStringExtra("username");

        welcome = findViewById(R.id.welcome);

        welcome.setText(value+"Welcome home");



    }
}
