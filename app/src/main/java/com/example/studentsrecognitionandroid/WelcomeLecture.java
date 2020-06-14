package com.example.studentsrecognitionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeLecture extends AppCompatActivity {
    private TextView welcome;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_lecture);


        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


        Intent intent = getIntent();

        int value = sharedPreferences.getInt("id",0);

        String em = sharedPreferences.getString("email",null);



        welcome = findViewById(R.id.welcome);

        welcome.setText(value+"Welcome home");



    }
}
