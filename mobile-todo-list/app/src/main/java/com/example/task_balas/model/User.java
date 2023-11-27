package com.example.task_balas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public User(){}
    public User(String name){
        this.name = name;
    }
    String name;
}
