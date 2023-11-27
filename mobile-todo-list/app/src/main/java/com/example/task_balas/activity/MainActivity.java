package com.example.task_balas.activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_balas.R;
import com.example.task_balas.config.RetrofitConfig;
import com.example.task_balas.model.Task;
import com.example.task_balas.adapter.TaskAdapter;
import com.example.task_balas.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList = new ArrayList<>();
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String userName = getUsername();
//        Call<String> call =  new RetrofitConfig().getTaskService().(new User(userName));
        // Sample tasks
        taskList.add(new Task("Task 1"));
        taskList.add(new Task("Task 2"));
        taskList.add(new Task("Task 3"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(taskAdapter);
    }

    private String getUsername() {
        Bundle extras =  getIntent().getExtras();
        if(extras != null) {
            return extras.getString("userName");
        }
        return null;
    }

    public void addTask(android.view.View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }

}
