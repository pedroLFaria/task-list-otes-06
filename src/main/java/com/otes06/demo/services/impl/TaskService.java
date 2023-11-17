package com.otes06.demo.services.impl;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.repositories.TaskRepository;
import com.otes06.demo.services.ITaskService;

import java.util.List;
import java.util.Map;

public class TaskService implements ITaskService {

    private TaskRepository repository;

    public TaskService(){
        repository = new TaskRepository();
    }
    @Override
    public String createTask(TaskDto task) {
        return repository.createTask(task);
    }
    
    @Override
    public TaskDto getTaskById(String id) {
        return repository.getTaskById(id);
    }

    @Override
    public List<TaskDto> getTaskByUserId(String userId) {
        return repository.getTaskByUserId(userId);
    }

    @Override
    public Map<String, TaskDto> getTasks() {
        return repository.getTaskTable();
    }
    
    @Override
    public TaskDto updateTask(String id, TaskDto task) {
        return repository.updateTask(id, task);
    }
}
