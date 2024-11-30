package com.example.todo_list_sap_case.service;

import com.example.todo_list_sap_case.exception.TodoNotFoundException;
import com.example.todo_list_sap_case.model.Todo;
import com.example.todo_list_sap_case.model.TodoList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {

    private final TodoList todoList;
    private final AtomicLong idCounter = new AtomicLong(1);
    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

    @Autowired
    public TodoService(TodoList todoList) {
        this.todoList = todoList;
    }

    public List<Todo> getAllTodos() {
        logger.info("Fetching all todos");
        return todoList.getTodos();
    }

    public Todo createTodo(String task) {
        Long id = idCounter.getAndIncrement();
        Todo todo = new Todo(id, task, false);
        todoList.addTodo(todo);
        logger.info("Created new todo with ID: {}", id);
        return todo;
    }

    public void deleteTodoById(Long id) {
        boolean deleted = todoList.removeTodoById(id);
        if (!deleted) {
            logger.warn("Failed to delete todo with ID {}: not found", id);
            throw new TodoNotFoundException("Todo with ID " + id + " not found");
        }
        logger.info("Todo with ID {} deleted", id);
    }

    public void completeTodoById(Long id) {
        boolean completed = todoList.completeTodoById(id);
        if (!completed) {
            logger.warn("Failed to complete todo with ID {}: not found", id);
            throw new TodoNotFoundException("Todo with ID " + id + " not found");
        }
        logger.info("Todo with ID {} marked as completed", id);
    }

    public void incompleteTodoById(Long id) {
        boolean markedIncomplete = todoList.incompleteTodoById(id);
        if (!markedIncomplete) {
            logger.warn("Failed to incomplete todo with ID {}: not found", id);
            throw new TodoNotFoundException("Todo with ID " + id + " not found");
        }
        logger.info("Todo with ID {} marked as incomplete", id);
    }
}

