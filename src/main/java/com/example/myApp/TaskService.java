package com.example.myApp;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;


@Service
public class TaskService {
    private final Map<String, Task1> storage = new ConcurrentHashMap<>();

    public Map<String, Task1> getStorage() {
        return storage;
    }

    public void addTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле title обязательно для заполнения");
        }
        Task1 task = new Task1(title, description);
        storage.put(task.getId(), task);
    }

    public String getTask(String id) {
        Task1 task = storage.get(id);
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
        Task1 updated = new Task1(title, description);
        storage.put(id, updated);
    }

    static class Task1 {
        private String title;
        private String description;
        private boolean completed;
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

        public Task1(String title, String description) {
            this.completed = false;
            this.description = description;
            this.title = title;
            this.id = UUID.randomUUID().toString();
        }
    }
}