package com.otes06.demo.controllers;

import com.otes06.demo.converters.UserEntityToDtoConverter;
import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.services.IUserService;
import com.otes06.demo.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService service;
    private final UserEntityToDtoConverter converter;

    @Autowired
    public UserController(UserService service, UserEntityToDtoConverter converter){
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable String id){
        return ResponseEntity.ok(converter.convert(service.getUserById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(
                service.getUsers()
                .entrySet().stream().map(converter::convert)
                .collect(Collectors.toList())
        );
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
