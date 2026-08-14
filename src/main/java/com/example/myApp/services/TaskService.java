package com.example.myApp.services;

import com.example.myApp.Task;
import com.example.myApp.TaskRepository;
import com.example.myApp.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class TaskService {
    private final TaskRepository taskRepository; // поле класса

    public TaskService(TaskRepository taskRepository) { // конструктор
        this.taskRepository = taskRepository;
    }

    public void addTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле title обязательно для заполнения");
        }
        Task task = new Task(title, description);
        storage.put(task.getId(), task);
    }

        public Task getTask(String id) {
            return taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));
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