package com.example.myApp.controller;


import com.example.myApp.Task;
import com.example.myApp.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/tasks")
    public  List<Task> getAllTasks() {
        return taskService.PrintAll();
    }

    @GetMapping("/api/tasks/{id}")
    public Task getTask(@PathVariable UUID id) {
        return taskService.getTask(id);
    }

    @PostMapping("/api/tasks")
    public Task addTask(@RequestParam String title, @RequestParam String description) {
        return taskService.addTask(title, description);
    }

    @PutMapping("/api/tasks/{id}")
    public Task changeTask(@PathVariable UUID id, @RequestParam String title, @RequestParam String description) {
        return taskService.changeTask(id, title, description);
    }

    @DeleteMapping("/api/tasks/{id}")
    public String deleteTask(@PathVariable UUID id) {
         taskService.removeTask(id);
         return "Задача удалена";
    }

}