package com.example.myApp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Task {
    private  String title;
    private  String description;
    private  boolean completed;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
        this.title = title;
        this.description = description;
    }

    public Task() {}
}