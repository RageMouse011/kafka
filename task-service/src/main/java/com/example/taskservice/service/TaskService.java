package com.example.taskservice.service;

import com.example.taskservice.model.Task;
import com.example.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public void addDefaultTaskToNewUser(String userName) {
        Task defaultTask = new Task();
        defaultTask.setName("Welcome!");
        defaultTask.setDescription("Go to the sidebar and check our possibilities");
        defaultTask.setStartDate(new Date());
        defaultTask.setUsersNames(List.of(userName));
        taskRepository.save(defaultTask);
    }

    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new NullPointerException("Task with id " + id + " not found");
    }

    public List<Task> getTasksByUsername(String userName) {
        return taskRepository.findTasksByUserName(userName);
    }
}
