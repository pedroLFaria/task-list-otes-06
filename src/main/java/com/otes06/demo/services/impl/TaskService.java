package com.otes06.demo.services.impl;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.repositories.TaskRepository;
import com.otes06.demo.services.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskService implements ITaskService {


    private TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository){
        this.repository = repository;
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
        return repository.getTaskByUserName(userId);
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
