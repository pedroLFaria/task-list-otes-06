package com.otes06.demo.services;

import com.otes06.demo.dtos.TaskDto;

import java.util.List;
import java.util.Map;

public interface ITaskService {
    public TaskDto getTaskById(String Id);
    public List<TaskDto> getTaskByUserId(String id);
    public Map<String, TaskDto> getTasks();
    public String createTask(TaskDto task);
    public TaskDto updateTask(String id, TaskDto task);
}
