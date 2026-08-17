package com.example.myApp.services;

import com.example.myApp.Task;
import com.example.myApp.TaskRepository;
import com.example.myApp.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class TaskService {
    private final TaskRepository taskRepository; // поле класса

    public TaskService(TaskRepository taskRepository) { // конструктор
        this.taskRepository = taskRepository;
    }
    @Transactional
    public Task addTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Поле title обязательно для заполнения");
        }
        Task task = new Task(title, description);
        return taskRepository.save(task);
    }
    @Transactional(readOnly = true)
        public Task getTask(UUID id) {
            return taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException(id.toString()));
    }
    @Transactional
    public void removeTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id.toString());
        }
        taskRepository.deleteById(id);
    }
    @Transactional
    public Task changeTask(UUID id, String title, String description) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id.toString());
        }
        Task updated = new Task(title, description);
        taskRepository.save(updated);
        return updated;
    }
    public List<Task> PrintAll() {
        return taskRepository.findAll();
    }
}