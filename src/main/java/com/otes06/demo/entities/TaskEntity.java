package com.otes06.demo.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskEntity {
    String text;
    int position;
    String userName;
    boolean isCompleted;
}
