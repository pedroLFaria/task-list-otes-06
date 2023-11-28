package com.otes06.demo.converters;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.entities.TaskEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaskEntityToDtoConverter implements Converter<Map.Entry<String, TaskEntity>, TaskDto> {

    @Override
    public TaskDto convert(Map.Entry<String, TaskEntity> source) {
        return TaskDto.builder()
                .userName(source.getValue().getUserName())
                .text(source.getValue().getText())
                .position(source.getValue().getPosition())
                .id(source.getKey())
                .isCompleted(source.getValue().isCompleted())
                .build();
    }
}
