package com.otes06.demo.services;

import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.entities.UserEntity;

import java.util.Map;

public interface IUserService {
    public Map.Entry<String, UserEntity> getUserById(String id);
    public Map<String, UserEntity> getUsers();
    public UserDto createUser(UserDto user);
    public UserDto updateUser(String id, UserDto user);
}
