package com.example.studentsrecognitionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private JsonPlaceHolder jsonPlaceHolder;
    private Users users;

    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);


        Button btn = findViewById(R.id.loginButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });





    }

    public void loginUser(){
        username = findViewById(R.id.username);
        final String usern = username.getText().toString();
        password = findViewById(R.id.password);

        String pass = password.getText().toString();


        Users users = new Users(usern,pass);


        Call<Users> call = jsonPlaceHolder.loginUser(users);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){

                    Intent intent = new Intent(LoginActivity.this,WelcomeLecture.class);
                    intent.putExtra("username",usern);
                    startActivity(intent);

                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });




    }
}
