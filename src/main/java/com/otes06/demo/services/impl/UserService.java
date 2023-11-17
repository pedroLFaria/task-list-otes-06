package com.otes06.demo.services.impl;

import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.repositories.UserRepository;
import com.otes06.demo.services.IUserService;

import java.util.Map;

public class UserService implements IUserService {

    private UserRepository repository;

    public UserService(){
        repository = new UserRepository();
    }
    @Override
    public String createUser(UserDto user) {
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
