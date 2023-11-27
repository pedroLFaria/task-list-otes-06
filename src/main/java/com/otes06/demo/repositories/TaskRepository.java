package com.otes06.demo.repositories;

import com.otes06.demo.dtos.TaskDto;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Data
@Component
public class TaskRepository {

    private Map<String, TaskDto> taskTable;
    private IdGenerator<TaskDto> idGenerator;
    private UserRepository userRepository;


    public TaskRepository(UserRepository userRepository){
        taskTable = new HashMap<>();
        idGenerator = new IdGenerator<>();
        this.userRepository = userRepository;
    }

    public String createTask(TaskDto newTask){
        String newTaskId = idGenerator.getRandomId(taskTable);
        int maxId = taskTable.entrySet().stream().map(Map.Entry::getValue).filter(t -> t.getUserName().equals(newTask.getUserName())).max(Comparator.comparing(TaskDto::getPosition)).map(TaskDto::getPosition).orElse(0);
        newTask.setPosition(maxId + 1);
        taskTable.put(newTaskId, newTask);
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

    public List<TaskDto> getTaskByUserName(String userName) {
        var maybeTasks = taskTable.values().stream().filter(taskDto -> taskDto.getUserName().equals(userName)).collect(Collectors.toList());
        if(maybeTasks.isEmpty()){
            throw new HttpServerErrorException(HttpStatusCode.valueOf(404), "Not found!");
        }
        return maybeTasks;
    }
}
