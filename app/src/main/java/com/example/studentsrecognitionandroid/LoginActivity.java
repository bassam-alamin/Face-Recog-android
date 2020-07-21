package com.example.studentsrecognitionandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private JsonPlaceHolder jsonPlaceHolder;
    private String email;
    private Users users;

    private EditText username;
    private EditText password;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);



        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Retrofit retrofit = new Retrofit.Builder()
                //this is for local host when url is 127.0.0.0
//                .baseUrl("http://10.0.2.2:8000/api/")
                .baseUrl("http://192.168.0.17:8000/api/")
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


    public void getUser(String n){
        Call<Users> call = jsonPlaceHolder.getLoggedUser(n);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {


                users = response.body();
                int id = users.getId();
                email = users.getEmail();

                sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);



                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id", id);
                editor.putString("email",email);
                editor.putString("username",users.getUsername());
                editor.apply();



                Toast toast = Toast.makeText(getApplicationContext(),users.getUsername(),Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(LoginActivity.this,WelcomeLecture.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });



    }

    public void loginUser(){
        username = findViewById(R.id.username);
        final String usern = username.getText().toString();
        password = findViewById(R.id.password);
        String pass = password.getText().toString();

        final Users users = new Users(usern,pass);

        Call<Users> call = jsonPlaceHolder.loginUser(users);


        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()){


                    getUser(usern);


//                    Call<Users> call2 = jsonPlaceHolder.getLoggedUser(usern);
//
//                    call2.enqueue(new Callback<Users>() {
//                        @Override
//                        public void onResponse(Call<Users> call, Response<Users> response) {
//
//
//
//                                Users users1 = response.body();
//
//                                int id = users1.getId();
//                                email = users.getEmail();
//
//                                Toast toast = Toast.makeText(getApplicationContext(),email,Toast.LENGTH_SHORT);
//                                toast.show();
//
//                                sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
//
//
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putInt("id", id);
//                                editor.putString("email","nyoka");
//                                editor.apply();
//
//                            }
//
//
//
//
//                        @Override
//                        public void onFailure(Call<Users> call, Throwable t) {
//
//                            Toast toast = Toast.makeText(getApplicationContext(),"Kuku ni mbaya",Toast.LENGTH_SHORT);
//                            toast.show();
//
//
//
//
//                        }
//                    });



                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });




    }
}
