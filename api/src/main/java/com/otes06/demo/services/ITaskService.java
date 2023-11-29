package com.otes06.demo.services;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.entities.TaskEntity;

import java.util.Map;

public interface ITaskService {
    public Map.Entry<String, TaskEntity> getTaskById(String Id);
    public Map<String, TaskEntity> getTaskByUserId(String id);
    public Map<String, TaskEntity> getTasks();
    public TaskDto createTask(TaskDto task);
    public TaskDto updateTask(String id, TaskDto task);
    Map.Entry<String, TaskEntity> delete(String id);
}
