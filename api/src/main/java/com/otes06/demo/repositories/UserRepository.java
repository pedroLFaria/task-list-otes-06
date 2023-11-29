package com.otes06.demo.repositories;

import com.otes06.demo.converters.UserDtoToEntityConverter;
import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.entities.UserEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class UserRepository {

    private Map<String, UserEntity> userTable;
    private IdGenerator<UserEntity> idGenerator;
    private UserDtoToEntityConverter converter;

    public UserRepository(UserDtoToEntityConverter converter){
        userTable = new HashMap<>();
        idGenerator = new IdGenerator<>();
        this.converter = converter;
    }

    public UserDto createUser(UserDto newUser){
        String newUserId = idGenerator.getRandomId(userTable);
        userTable.put(newUserId, converter.convert(newUser));
        newUser.setId(newUserId);
        return newUser;
    }
    
    public Map.Entry<String, UserEntity> getUserById(String id){
        return userTable.entrySet().stream().filter(u -> u.getKey().equals(id)).findFirst().orElse(null);
    }

    public Map<String, UserEntity> getUsers(){
        return userTable;
    }

    public UserDto updateUser(String id, UserDto userDto){
        if(userTable.get(id) == null){
            throw new IllegalArgumentException("Usuário não existe!");
        }
        userTable.put(id, converter.convert(userDto));
        userDto.setId(id);
        return userDto;
    }
}
