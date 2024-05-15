package com.example.taskservice.controller;

import com.example.taskservice.model.Task;
import com.example.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Task getTaskById(@RequestParam Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/byUsername")
    public List<Task> getTasksByUsername(@RequestParam String userName) {
        return taskService.getTasksByUsername(userName);
    }
}
