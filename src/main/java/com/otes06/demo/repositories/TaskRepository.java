package com.otes06.demo.repositories;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.dtos.TaskDto;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.config.Task;
import org.springframework.web.client.HttpServerErrorException;

import javax.management.AttributeNotFoundException;
import java.net.HttpRetryException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
public class TaskRepository {

    private Map<String, TaskDto> taskTable;
    private IdGenerator<TaskDto> idGenerator;

    public TaskRepository(){
        taskTable = new HashMap<>();
        idGenerator = new IdGenerator<>();
    }

    public String createTask(TaskDto TaskDto){
        String newTaskId = idGenerator.getRandomId(taskTable);
        taskTable.put(newTaskId, TaskDto);
        return newTaskId;
    }
    
    public TaskDto getTaskById(String Id){
        return taskTable.get(Id);
    }

    public Map<String, TaskDto> getTasks(){
        return taskTable;
    }

    public TaskDto updateTask(String id, TaskDto taskDto){
        TaskDto task = taskTable.get(id);
        if(task == null){
            throw new IllegalArgumentException("Tarefa não existe!");
        }
        taskTable.put(id, taskDto);
        return taskDto;
    }

    public List<TaskDto> getTaskByUserId(String userId) {
        var maybeTasks = taskTable.values().stream().filter(taskDto -> taskDto.getUserId().equals(userId)).collect(Collectors.toList());
        if(maybeTasks.isEmpty()){
            throw new HttpServerErrorException(HttpStatusCode.valueOf(404), "Not found!");
        }
        return maybeTasks;
    }
}
