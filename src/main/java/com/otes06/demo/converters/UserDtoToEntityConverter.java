package com.otes06.demo.converters;

import com.otes06.demo.dtos.UserDto;
import com.otes06.demo.entities.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToEntityConverter implements Converter<UserDto, UserEntity> {
    @Override
    public UserEntity convert(UserDto source) {
        return UserEntity.builder()
                .name(source.getName())
                .build();
    }
}
