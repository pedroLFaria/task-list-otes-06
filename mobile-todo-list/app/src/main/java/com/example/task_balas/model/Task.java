package com.example.task_balas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements Serializable {
    String id;
    private String text;
    private boolean isCompleted;
    int position;
    String userName;

    public Task(){}

    public Task(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    public Task(String id, String text, boolean isCompleted, int position, String userName) {
        this.id = id;
        this.text = text;
        this.isCompleted = isCompleted;
        this.position = position;
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
