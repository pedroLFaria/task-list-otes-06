package com.example.task_balas.config;

import com.example.task_balas.services.TaskService;
import com.example.task_balas.services.UserService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8082/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public TaskService getTaskService() {
        return this.retrofit.create(TaskService.class);
    }

    public UserService getUserService() {
        return this.retrofit.create(UserService.class);
    }
}
