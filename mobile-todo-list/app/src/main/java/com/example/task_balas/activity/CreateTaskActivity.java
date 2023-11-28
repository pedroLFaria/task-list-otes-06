package com.example.task_balas.activity;// CreateTaskActivity.java
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task_balas.R;
import com.example.task_balas.config.RetrofitConfig;
import com.example.task_balas.model.Task;
import com.example.task_balas.services.TaskService;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText taskNameInput;
    private TaskService service;
    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userName = getUsername();
        service = new RetrofitConfig().getTaskService();
        setContentView(R.layout.activity_create_task);
        taskNameInput = findViewById(R.id.taskNameInput);
        // Set up the action bar with a back arrow
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveTask(android.view.View view) {
        Task newTask = new Task();
        newTask.setCompleted(false);
        newTask.setText(taskNameInput.getText().toString());
        newTask.setUserName(userName);
        service.createTask(newTask).enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", response.body());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });

    }

    private String getUsername() {
        Bundle extras =  getIntent().getExtras();
        if(extras != null) {
            return extras.getString("userName");
        }
        return null;
    }
}
