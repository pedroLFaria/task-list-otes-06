package com.otes06.demo.services.impl;

import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.repositories.UserRepository;
import com.otes06.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements IUserService {


    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public UserDto createUser(UserDto user) {
        return repository.createUser(user);
    }
    
    @Override
    public UserDto getUserById(String id) {
        return repository.getUserById(id);
    }
    
    @Override
    public Map<String, UserDto> getUsers() {
        return repository.getUserTable();
    }
    
    @Override
    public UserDto updateUser(String id, UserDto user) {
        return repository.updateUser(id, user);
    }
}
