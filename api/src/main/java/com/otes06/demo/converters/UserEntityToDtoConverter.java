package com.otes06.demo.converters;

import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.entities.TaskEntity;
import com.otes06.demo.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserEntityToDtoConverter implements Converter<Map.Entry<String, UserEntity>, UserDto> {

    @Override
    public UserDto convert(Map.Entry<String, UserEntity> source) {
        return UserDto.builder()
                .id(source.getKey())
                .name(source.getValue().getName())
                .build();
    }
}
