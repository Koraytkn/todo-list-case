package com.example.todo_list_sap_case.controller;

import com.example.todo_list_sap_case.model.Todo;
import com.example.todo_list_sap_case.model.TodoDTO;
import com.example.todo_list_sap_case.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class TodoListControllerTest {

    @InjectMocks
    private TodoListController todoListController;

    @Mock
    private TodoService todoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTodos() {
        Todo todo1 = new Todo(1L, "Task 1", false);
        Todo todo2 = new Todo(2L, "Task 2", true);
        when(todoService.getAllTodos()).thenReturn(List.of(todo1, todo2));

        ResponseEntity<List<TodoDTO>> response = todoListController.getAllTodos();

        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Task 1", response.getBody().get(0).getTask());
        verify(todoService, times(1)).getAllTodos();
    }

    @Test
    void testCreateTodo_ValidTask() {
        String task = "New Task";

        ResponseEntity<String> response = todoListController.createTodo(task);

        assertEquals(CREATED, response.getStatusCode());
        assertEquals("Todo created successfully", response.getBody());
        verify(todoService, times(1)).createTodo(task);
    }

    @Test
    void testCreateTodo_InvalidTask() {
        String emptyTask = "";

        ResponseEntity<String> response = todoListController.createTodo(emptyTask);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertEquals("Task cannot be empty", response.getBody());
        verify(todoService, never()).createTodo(anyString());
    }

    @Test
    void testDeleteTodoById_Success() {
        Long todoId = 1L;

        ResponseEntity<String> response = todoListController.deleteTodoById(todoId);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Todo deleted successfully", response.getBody());
        verify(todoService, times(1)).deleteTodoById(todoId);
    }

    @Test
    void testCompleteTodoById_Success() {
        Long todoId = 1L;

        ResponseEntity<String> response = todoListController.completeTodoById(todoId);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Todo marked as completed", response.getBody());
        verify(todoService, times(1)).completeTodoById(todoId);
    }

    @Test
    void testIncompleteTodoById_Success() {
        Long todoId = 1L;

        ResponseEntity<String> response = todoListController.incompleteTodoById(todoId);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Todo marked as incomplete", response.getBody());
        verify(todoService, times(1)).incompleteTodoById(todoId);
    }
}
