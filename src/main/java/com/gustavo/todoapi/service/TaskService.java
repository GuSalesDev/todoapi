package com.gustavo.todoapi.service;

import com.gustavo.todoapi.dto.TaskRequest;
import com.gustavo.todoapi.dto.TaskResponse;
import com.gustavo.todoapi.entity.Task;
import com.gustavo.todoapi.entity.User;
import com.gustavo.todoapi.repository.TaskRepository;
import com.gustavo.todoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }

    public List<TaskResponse> findAll(String email) {
        return taskRepository.findByUserId(getUser(email).getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TaskResponse create(String email, TaskRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .user(getUser(email))
                .build();
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse toggleComplete(String email, Long taskId) {
        Long userId = getUser(email).getId();
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        task.setCompleted(!task.isCompleted());
        return toResponse(taskRepository.save(task));
    }

    public void delete(String email, Long taskId) {
        Long userId = getUser(email).getId();
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        taskRepository.delete(task);
    }
}