package com.example.studentsrecognitionandroid;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolder {


    //get a single user ,single meaning an java object
    @GET("user/{id}")
    Call<Users> getUser(@Path("id") int id);

    @GET("users/{username}")
    Call<Users> getLoggedUser(@Path("username") String username);

    @POST("login/")
    Call<Users> loginUser(@Body Users users);


    @GET("units")
    Call<List<Unit>> getUnits();

//    @POST("departments/")
//    Call<Department> createDepartment(@Body Department department);






}
