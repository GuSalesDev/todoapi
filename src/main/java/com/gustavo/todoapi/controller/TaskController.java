package com.gustavo.todoapi.controller;

import com.gustavo.todoapi.dto.TaskRequest;
import com.gustavo.todoapi.dto.TaskResponse;
import com.gustavo.todoapi.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAll(Authentication auth) {
        return ResponseEntity.ok(taskService.findAll(auth.getName()));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(Authentication auth,
                                               @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.create(auth.getName(), request));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<TaskResponse> toggle(Authentication auth, @PathVariable Long id) {
        return ResponseEntity.ok(taskService.toggleComplete(auth.getName(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth, @PathVariable Long id) {
        taskService.delete(auth.getName(), id);
        return ResponseEntity.noContent().build();
    }
}