package com.example.task_balas.activity;// CreateTaskActivity.java
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task_balas.R;

public class CreateTaskActivity extends AppCompatActivity {

    private EditText taskNameInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // Handle arrow back button click
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close the activity and return to the main screen
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveTask(android.view.View view) {
        // Handle the save button click (you can add logic if needed)
        finish(); // Close the activity after saving
    }
}
