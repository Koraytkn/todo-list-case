package com.example.todo_list_sap_case.model;

import java.time.LocalDateTime;

public class Todo implements Comparable<Todo> {
    private Long id;
    private String task;
    private boolean completed;
    private LocalDateTime createdAt; // Time field for sorting

    public Todo(Long id, String task, boolean completed) {
        this.id = id;
        this.task = task;
        this.completed = completed;
        this.createdAt = LocalDateTime.now(); // Set creation time upon object creation
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int compareTo(Todo other) {
        // First compare by completion status: incomplete (false) is smaller than completed (true)
        if (this.completed != other.completed) {
            return this.completed ? 1 : -1;
        }
        return this.createdAt.compareTo(other.createdAt); // Then time based
    }
}
