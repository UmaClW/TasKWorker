package com.example.myApp.services;

import com.example.myApp.Task;
import com.example.myApp.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;


@Service
public class TaskService {
    private final Map<String, Task> storage = new ConcurrentHashMap<>();

    public Map<String, Task> getStorage() {
        return storage;
    }

    public void addTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле title обязательно для заполнения");
        }
        Task task = new Task(title, description);
        storage.put(task.getId(), task);
    }

    public String getTask(String id) {
        Task task = storage.get(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task.printTask();
    }

    public void removeTask(String id) {
        if (!storage.containsKey(id)) {
            throw new TaskNotFoundException(id);
        }
        storage.remove(id);
    }

    public void changeTask(String id, String title, String description) {
        if (!storage.containsKey(id)) {
            throw new TaskNotFoundException(id);
        }
        Task updated = new Task(title, description);
        storage.put(id, updated);
    }
}