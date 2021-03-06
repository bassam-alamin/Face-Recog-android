package com.example.studentsrecognitionandroid;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
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

    @GET("users/{staff_no}")
    Call<Users> getLoggedUser(@Path("staff_no") String staff_no);

    @POST("login/")
    Call<Users> loginUser(@Body Users users);

    @GET("units")
    Call<List<Unit>> getUnits();

    @GET("students/{imagestring}")
    Call<Student> getStudent(@Path(value = "imagestring",encoded = true) String imagestring);

    @GET("student/booking/")
    Call<Booking> findBooking(
            @Query("session_id") int session_id,
            @Query("student_id") int student_id
    );

    @PATCH("student/booking/{pk}")
    Call<Booking> markAttended(@Path("pk") int pk);

    @GET("exam-session/{pk}")
    Call<List<ExaminationSession>> getExamSessions(@Path("pk") int pk);

//    Reports Url
    @GET("attended/{pk}")
    Call<List<ReportsClass>> getAttended(@Path("pk") int pk);






//    @POST("departments/")
//    Call<Department> createDepartmenofitt(@Body Department department);


}
