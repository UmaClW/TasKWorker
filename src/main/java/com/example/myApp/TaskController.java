package com.example.myApp;


import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/tasks")
    public Map<String, TaskService.Task1> getAllTasks() {
        return taskService.getStorage();
    }

    @GetMapping("/api/tasks/{id}")
    public String getTask(@PathVariable String id) {
        return taskService.getTask(id);
    }

    @PostMapping("/api/tasks")
    public String addTask(@RequestParam String title, @RequestParam String description) {
        taskService.addTask(title, description);
        return "Задача успешно добавлена";
    }

    @PutMapping("/api/tasks/{id}")
    public String changeTask(@PathVariable String id, @RequestParam String title, @RequestParam String description) {
        taskService.changeTask(id, title, description);
        return "Задача успешно изменена";
    }

    @DeleteMapping("/api/tasks/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.removeTask(id);
        return "Задача успешно удалена";
    }

}