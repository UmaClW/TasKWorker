package com.example.myApp;

import jakarta.persistence.Entity;

import java.util.UUID;

public class Task {
    private final String title;
    private final String description;
    private final boolean completed;
    private final String id;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }

    public String printTask() {
        return "Идентификатор: " + id + " Имя: " + title + " Описание: " + description + " Статус: " + completed + "\n";
    }

    public Task(String title, String description) {
        this.completed = false;
        this.description = description;
        this.title = title;
        this.id = UUID.randomUUID().toString();
    }
}