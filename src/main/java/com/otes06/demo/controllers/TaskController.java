package com.otes06.demo.controllers;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.services.ITaskService;
import com.otes06.demo.services.impl.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getTaskById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskDto>> getByUserId(@PathVariable String userId){
        return ResponseEntity.ok(service.getTaskByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<Map<String, TaskDto>> getAll(){
        return ResponseEntity.ok(service.getTasks());
    }

    @PostMapping
    public ResponseEntity<String> post(@RequestBody TaskDto dto){
        return ResponseEntity.ok(service.createTask(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> put(@PathVariable("id") String id, @RequestBody TaskDto dto){
        return ResponseEntity.ok(service.updateTask(id, dto));
    }
}
