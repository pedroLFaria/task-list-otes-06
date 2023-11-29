package com.otes06.demo.repositories;

import com.otes06.demo.converters.TaskDtoToEntityConverter;
import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.entities.TaskEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;
import java.util.stream.Collectors;


@Data
@Component
public class TaskRepository {

    private Map<String, TaskEntity> taskTable;
    private IdGenerator<TaskEntity> idGenerator;
    private UserRepository userRepository;
    private TaskDtoToEntityConverter dtoToEntityConverter;

    @Autowired
    public TaskRepository(UserRepository userRepository, TaskDtoToEntityConverter dtoToEntityConverter) {
        taskTable = new HashMap<>();
        idGenerator = new IdGenerator<>();
        this.userRepository = userRepository;
        this.dtoToEntityConverter = dtoToEntityConverter;
    }

    public TaskDto createTask(TaskDto newTask){
        String newTaskId = idGenerator.getRandomId(taskTable);
        int maxId = taskTable.entrySet().stream().map(Map.Entry::getValue)
                .filter(t -> t.getUserName().equals(newTask.getUserName()))
                .max(Comparator.comparing(TaskEntity::getPosition))
                .map(TaskEntity::getPosition).orElse(0);
        newTask.setPosition(maxId + 1);
        taskTable.put(newTaskId, dtoToEntityConverter.convert(newTask));
        newTask.setId(newTaskId);
        return newTask;
    }
    
    public Map.Entry<String, TaskEntity> getTaskById(String id){
        return taskTable.entrySet().stream().filter(e -> e.getKey() == id).findFirst().orElse(null);
    }

    public Map<String, TaskEntity> getTasks(){
        return taskTable;
    }

    public TaskDto updateTask(String id, TaskDto taskDto){
        TaskEntity task = taskTable.get(id);
        if(task == null){
            throw new IllegalArgumentException("Tarefa não existe!");
        }
        taskTable.put(id, dtoToEntityConverter.convert(taskDto));
        taskDto.setId(id);
        return taskDto;
    }

    public Map<String, TaskEntity> getTaskByUserName(String userName) {
        var maybeTasks = taskTable.entrySet().stream().filter(taskDto -> taskDto.getValue().getUserName().equals(userName)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if(maybeTasks.isEmpty()){
            return new HashMap<>();
        }
        return maybeTasks;
    }

    public Map.Entry<String, TaskEntity> deleteTask(String id) {
        var entity = taskTable.remove(id);
        return Map.entry(id, entity);
    }
}
