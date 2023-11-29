package com.example.task_balas.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_balas.R;
import com.example.task_balas.config.RetrofitConfig;
import com.example.task_balas.model.Task;
import com.example.task_balas.services.TaskService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private TaskService service;

    public TaskAdapter() {
        this.taskList = new ArrayList<>();
        this.service = new RetrofitConfig().getTaskService();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskText.setText(task.getText());
        holder.completeButton.setChecked(task.isCompleted());
        holder.completeButton.setOnClickListener(v -> {
            task.setCompleted(!task.isCompleted());
            service.updateTask(task.getId(), task).enqueue(updateCallback(position));
        });
        holder.deleteButton.setOnClickListener(v -> {
            service.deleteTask(task.getId()).enqueue(deleteCallback(task));
        });
    }

    @NonNull
    private Callback<Task> updateCallback(int position) {
        return new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                taskList.set(position, response.body());
                notifyItemChanged(position);
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        };
    }

    @NonNull
    private Callback<Task> deleteCallback(Task task) {
        return new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                taskList.remove(task);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        };
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskText;
        CheckBox completeButton;
        ImageButton deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskText = itemView.findViewById(R.id.taskText);
            completeButton = itemView.findViewById(R.id.completeButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
