package com.example.task_balas.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_balas.R;
import com.example.task_balas.adapter.TaskAdapter;
import com.example.task_balas.config.RetrofitConfig;
import com.example.task_balas.model.Task;
import com.example.task_balas.services.TaskService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TaskAdapter taskAdapter;

    private TaskService service;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.service = new RetrofitConfig().getTaskService();
        setContentView(R.layout.activity_main);
        userName = getUsername();
        service.getTaskByUser(userName).enqueue(getTaskCallback());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
    }

    private String getUsername() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString("userName");
        }
        return null;
    }

    public void addTask(android.view.View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        intent.putExtra("userName", userName);
        createTaskActivityCallback.launch(intent);
    }

    ActivityResultLauncher<Intent> createTaskActivityCallback = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {

                if (result.getResultCode() != Activity.RESULT_OK) {
                    //TODO Tratar caso de erro
                    return;
                }
                Intent intent = result.getData();
                if (intent == null) {
                    //TODO caso venha sem intent
                    return;
                }
                Task newTask = (Task) intent.getSerializableExtra("task");
                taskAdapter.getTaskList().add(newTask);
                taskAdapter.notifyDataSetChanged();
            });
    @NonNull
    private Callback<List<Task>> getTaskCallback() {
        return new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                List<Task> taskList = response.body();
                taskAdapter.setTaskList(taskList);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

            }
        };
    }
}
