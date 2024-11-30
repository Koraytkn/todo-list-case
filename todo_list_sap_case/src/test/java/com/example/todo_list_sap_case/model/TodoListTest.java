package com.example.todo_list_sap_case.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    private TodoList todoList;

    @BeforeEach
    public void setup() {
        todoList = new TodoList();
    }

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1L, "Test Task", false);

        todoList.addTodo(todo);

        List<Todo> todos = todoList.getTodos();
        assertEquals(1, todos.size(), "Todo list should contain 1 item");
        assertTrue(todos.contains(todo), "Todo list should contain the added todo");
    }

    @Test
    public void testGetTodosReturnsUnmodifiableCopy() {
        Todo todo = new Todo(1L, "Test Task", false);
        todoList.addTodo(todo);

        List<Todo> todos = todoList.getTodos();

        assertNotSame(todoList.getTodos(), todos, "Should return a new list copy");
    }

    @Test
    public void testRemoveTodoById_Success() {
        Todo todo = new Todo(1L, "Test Task", false);
        todoList.addTodo(todo);

        boolean result = todoList.removeTodoById(1L);

        assertTrue(result, "RemoveTodoById should return true for a valid ID");
        assertEquals(0, todoList.getTodos().size(), "Todo list should be empty after removal");
    }

    @Test
    public void testRemoveTodoById_Failure() {
        boolean result = todoList.removeTodoById(999L);

        assertFalse(result, "RemoveTodoById should return false for a non-existent ID");
    }

    @Test
    public void testCompleteTodoById_Success() {
        Todo todo = new Todo(1L, "Test Task", false);
        todoList.addTodo(todo);

        boolean result = todoList.completeTodoById(1L);

        assertTrue(result, "CompleteTodoById should return true for a valid ID");
        assertTrue(todo.isCompleted(), "Todo should be marked as completed");
    }

    @Test
    public void testCompleteTodoById_Failure() {
        boolean result = todoList.completeTodoById(999L);

        assertFalse(result, "CompleteTodoById should return false for a non-existent ID");
    }

    @Test
    public void testIncompleteTodoById_Success() {
        Todo todo = new Todo(1L, "Test Task", true); // Initially completed
        todoList.addTodo(todo);

        boolean result = todoList.incompleteTodoById(1L);

        assertTrue(result, "IncompleteTodoById should return true for a valid ID");
        assertFalse(todo.isCompleted(), "Todo should be marked as incomplete");
    }

    @Test
    public void testIncompleteTodoById_Failure() {
        boolean result = todoList.incompleteTodoById(999L);

        assertFalse(result, "IncompleteTodoById should return false for a non-existent ID");
    }

    @Test
    public void testTodosAreSorted() {
        Todo todo1 = new Todo(1L, "Task 1", false);
        Todo todo2 = new Todo(2L, "Task 2", true);
        Todo todo3 = new Todo(3L, "Task 3", false);

        todoList.addTodo(todo1);
        todoList.addTodo(todo2);
        todoList.addTodo(todo3);

        List<Todo> todos = todoList.getTodos();

        assertEquals(todo1, todos.get(0), "Incomplete todos should come first");
        assertEquals(todo3, todos.get(1), "Todos should be sorted by creation time");
        assertEquals(todo2, todos.get(2), "Completed todos should come last");
    }
}
