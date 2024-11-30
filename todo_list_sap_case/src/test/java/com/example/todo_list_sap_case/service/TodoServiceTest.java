package com.example.todo_list_sap_case.service;

import com.example.todo_list_sap_case.exception.TodoNotFoundException;
import com.example.todo_list_sap_case.model.Todo;
import com.example.todo_list_sap_case.model.TodoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoList todoList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTodo() {
        String task = "Test Task";
        Todo mockTodo = new Todo(1L, task, false);
        doNothing().when(todoList).addTodo(any(Todo.class));

        Todo createdTodo = todoService.createTodo(task);

        assertNotNull(createdTodo, "Created Todo should not be null");
        assertEquals(task, createdTodo.getTask(), "Todo task should match");
        verify(todoList, times(1)).addTodo(any(Todo.class));
    }

    @Test
    public void testGetAllTodos() {
        Todo todo1 = new Todo(1L, "Task 1", false);
        Todo todo2 = new Todo(2L, "Task 2", true);
        List<Todo> todos = List.of(todo1, todo2);
        when(todoList.getTodos()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();

        assertEquals(2, result.size(), "Should return 2 todos");
        assertTrue(result.contains(todo1), "Should contain Task 1");
        assertTrue(result.contains(todo2), "Should contain Task 2");
        verify(todoList, times(1)).getTodos();
    }

    @Test
    public void testDeleteTodoById_Success() {
        Long todoId = 1L;
        when(todoList.removeTodoById(todoId)).thenReturn(true);

        todoService.deleteTodoById(todoId);

        verify(todoList, times(1)).removeTodoById(todoId);
    }

    @Test
    public void testDeleteTodoById_NotFound() {
        Long todoId = 999L;
        when(todoList.removeTodoById(todoId)).thenReturn(false);

        TodoNotFoundException thrown = assertThrows(TodoNotFoundException.class, () -> todoService.deleteTodoById(todoId), "Expected TodoNotFoundException");
        assertEquals("Todo with ID 999 not found", thrown.getMessage(), "Exception message should match");
    }

    @Test
    public void testCompleteTodoById_Success() {
        Long todoId = 1L;
        when(todoList.completeTodoById(todoId)).thenReturn(true);

        todoService.completeTodoById(todoId);

        verify(todoList, times(1)).completeTodoById(todoId);
    }

    @Test
    public void testCompleteTodoById_NotFound() {
        Long todoId = 999L;
        when(todoList.completeTodoById(todoId)).thenReturn(false);

        TodoNotFoundException thrown = assertThrows(TodoNotFoundException.class, () -> todoService.completeTodoById(todoId), "Expected TodoNotFoundException");
        assertEquals("Todo with ID 999 not found", thrown.getMessage(), "Exception message should match");
    }

    @Test
    public void testIncompleteTodoById_Success() {
        Long todoId = 1L;
        when(todoList.incompleteTodoById(todoId)).thenReturn(true);

        todoService.incompleteTodoById(todoId);

        verify(todoList, times(1)).incompleteTodoById(todoId);
    }

    @Test
    public void testIncompleteTodoById_NotFound() {
        Long todoId = 999L;
        when(todoList.incompleteTodoById(todoId)).thenReturn(false);

        TodoNotFoundException thrown = assertThrows(TodoNotFoundException.class, () -> todoService.incompleteTodoById(todoId), "Expected TodoNotFoundException");
        assertEquals("Todo with ID 999 not found", thrown.getMessage(), "Exception message should match");
    }
}
