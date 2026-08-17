package com.example.myApp.controller;


import com.example.myApp.Task;
import com.example.myApp.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
         return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(task.getTitle(), task.getDescription()));
    }

    @PutMapping("/api/tasks/{id}")
    public Task changeTask(@PathVariable UUID id, @RequestBody Task task ) {
        return taskService.changeTask(id, task.getTitle(), task.getDescription());
    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
         taskService.removeTask(id);
        return ResponseEntity.noContent().build();
    }

}