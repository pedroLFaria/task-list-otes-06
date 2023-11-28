package com.example.task_balas.services;

import com.example.task_balas.model.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TaskService {
    @GET("task/user/{userId}")
    Call<List<Task>> getTaskByUser(@Path("userId") String userId);

    @POST("task")
    Call<Task> createTask(@Body Task task);

    @DELETE("task/{id}")
    Call<Task> deleteTask(@Path("id") String taskId);

    @PUT("task/{id}")
    Call<Task> updateTask(@Path("id") String taskId, @Body Task task);
}
