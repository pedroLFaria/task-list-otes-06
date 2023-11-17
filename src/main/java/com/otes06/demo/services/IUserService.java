package com.otes06.demo.services;

import com.otes06.demo.dtos.UserDto;

import java.util.Map;

public interface IUserService {
    public UserDto getUserById(String id);
    public Map<String, UserDto> getUsers();
    public String createUser(UserDto user);
    public UserDto updateUser(String id, UserDto user);
}
