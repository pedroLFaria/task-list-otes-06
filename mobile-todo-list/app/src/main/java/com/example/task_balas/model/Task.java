package com.example.task_balas.model;

public class Task {
    private String text;
    private boolean isCompleted;

    public Task(String text) {
        this.text = text;
        this.isCompleted = false;
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
}
