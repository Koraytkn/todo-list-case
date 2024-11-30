package com.example.todo_list_sap_case.controller;

import com.example.todo_list_sap_case.model.TodoDTO;
import com.example.todo_list_sap_case.service.TodoService;
import com.example.todo_list_sap_case.mapper.TodoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoListController {

    private final TodoService todoService;
    private static final Logger logger = LoggerFactory.getLogger(TodoListController.class);

    @Autowired
    public TodoListController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        logger.info("GET /todos request received");
        List<TodoDTO> todoDTOs = TodoMapper.toDtoList(todoService.getAllTodos());
        return new ResponseEntity<>(todoDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestParam String task) {
        if (task == null || task.trim().isEmpty()) {
            logger.warn("Invalid task input: Task is empty or null.");
            return new ResponseEntity<>("Task cannot be empty", HttpStatus.BAD_REQUEST);
        }

        logger.info("POST /todos request received with task: {}", task);
        todoService.createTodo(task);
        return new ResponseEntity<>("Todo created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
        logger.info("DELETE /todos/{} request received", id);
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<String> completeTodoById(@PathVariable Long id) {
        logger.info("PUT /todos/{}/complete request received", id);
        todoService.completeTodoById(id);
        return new ResponseEntity<>("Todo marked as completed", HttpStatus.OK);
    }

    @PutMapping("/{id}/incomplete")
    public ResponseEntity<String> incompleteTodoById(@PathVariable Long id) {
        logger.info("PUT /todos/{}/incomplete request received", id);
        todoService.incompleteTodoById(id);
        return new ResponseEntity<>("Todo marked as incomplete", HttpStatus.OK);
    }
}

