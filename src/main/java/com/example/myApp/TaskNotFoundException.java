package com.example.myApp;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String id) {
        super("Задача: " + id + " не найдена");
    }
}

