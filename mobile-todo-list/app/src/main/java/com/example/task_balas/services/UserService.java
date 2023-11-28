package com.example.task_balas.services;

import com.example.task_balas.model.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @GET("user")
    Call<Map<String,User>> userGet();

    @POST("user")
    Call<User> userCreate(@Body User user);

}
