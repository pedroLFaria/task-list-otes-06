package com.otes06.demo.controllers;

import com.otes06.demo.converters.TaskEntityToDtoConverter;
import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.services.ITaskService;
import com.otes06.demo.services.impl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService service;
    private TaskEntityToDtoConverter entityToDtoConverter;

    @Autowired
    public TaskController(TaskService service, TaskEntityToDtoConverter entityToDtoConverter){
        this.service = service;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable String id){
        return ResponseEntity.ok(entityToDtoConverter.convert(service.getTaskById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDto>> getByUserId(@PathVariable String userId){
        return ResponseEntity.ok(service.getTaskByUserId(userId)
                .entrySet().stream().map(e -> entityToDtoConverter.convert(e)).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(){
        return ResponseEntity.ok(service.getTasks()
                .entrySet().stream().map(e-> entityToDtoConverter.convert(e)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TaskDto> post(@RequestBody TaskDto dto){
        return ResponseEntity.ok(service.createTask(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> put(@PathVariable("id") String id, @RequestBody TaskDto dto){
        return ResponseEntity.ok(service.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(entityToDtoConverter.convert(service.delete(id)));
    }
}
