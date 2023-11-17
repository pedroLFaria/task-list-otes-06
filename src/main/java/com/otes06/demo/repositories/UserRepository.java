package com.otes06.demo.repositories;

import com.otes06.demo.dtos.UserDto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class UserRepository {

    private Map<String, UserDto> userTable;
    private IdGenerator<UserDto> idGenerator;

    public UserRepository(){
        userTable = new HashMap<>();
        idGenerator = new IdGenerator<>();
    }

    public String createUser(UserDto UserDto){
        String newUserId = idGenerator.getRandomId(userTable);
        userTable.put(newUserId, UserDto);
        return newUserId;
    }
    
    public UserDto getUserById(String id){
        return userTable.get(id);
    }

    public Map<String, UserDto> getUsers(){
        return userTable;
    }

    public UserDto updateUser(String id, UserDto userDto){
        UserDto user = userTable.get(id);
        if(user == null){
            throw new IllegalArgumentException("Tarefa não existe!");
        }
        userTable.put(id, userDto);
        return userDto;
    }
}
