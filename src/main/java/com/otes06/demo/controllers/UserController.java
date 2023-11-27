package com.otes06.demo.controllers;

import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.services.IUserService;
import com.otes06.demo.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable String id){
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<Map<String, UserDto>> getAll(){
        return ResponseEntity.ok(service.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> post(@RequestBody UserDto dto){
        return ResponseEntity.ok(service.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> put(@PathVariable("id") String id, @RequestBody UserDto dto){
        return ResponseEntity.ok(service.updateUser(id, dto));
    }
}
