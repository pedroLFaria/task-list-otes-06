package com.otes06.demo.converters;

import com.otes06.demo.dtos.TaskDto;
import com.otes06.demo.entities.TaskEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoToEntityConverter implements Converter<TaskDto, TaskEntity> {
    @Override
    public TaskEntity convert(TaskDto source) {
        return TaskEntity.builder()
                .userName(source.getUserName())
                .position(source.getPosition())
                .text(source.getText())
                .isCompleted(source.isCompleted())
                .build();
    }
}
