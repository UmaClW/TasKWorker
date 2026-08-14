package com.example.myApp.services;

import com.example.myApp.Task;
import com.example.myApp.TaskRepository;
import com.example.myApp.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class TaskService {
    private final TaskRepository taskRepository; // поле класса

    public TaskService(TaskRepository taskRepository) { // конструктор
        this.taskRepository = taskRepository;
    }
    @Transactional
    public void addTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле title обязательно для заполнения");
        }
        Task task = new Task(title, description);
        taskRepository.save(task);
    }
    @Transactional(readOnly = true)
        public Task getTask(String id) {
            return taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id));
    }
    @Transactional
    public void removeTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
    @Transactional
    public void changeTask(String id, String title, String description) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        Task updated = new Task(title, description);
        taskRepository.save(updated);
    }

    public Map<String, Task> getStorage() {
        return (Map<String, Task>) taskRepository;
    }
}