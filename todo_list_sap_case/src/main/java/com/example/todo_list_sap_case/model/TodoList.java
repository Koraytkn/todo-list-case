package com.example.todo_list_sap_case.model;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class TodoList { //singleton, in-memory storage component.
    private final List<Todo> todos = new ArrayList<>();
    private final Map<Long, Todo> todoMap = new HashMap<>(); // Quick lookup by ID

    public List<Todo> getTodos() {
        return new ArrayList<>(todos);
    }

    public void addTodo(Todo todo) {
        if (!todoMap.containsKey(todo.getId())) {
            todos.add(todo);
            todoMap.put(todo.getId(), todo);
            todos.sort(null); // Sort after addition
        }
    }

    public boolean removeTodoById(Long id) {
        Todo todo = todoMap.remove(id);
        if (todo != null) {
            todos.remove(todo);
            return true;
        }
        return false;
    }

    public boolean completeTodoById(Long id) {
        Todo todo = todoMap.get(id);
        if (todo != null && !todo.isCompleted()) {
            todo.setCompleted(true);
            todos.sort(null); // Re-sort after status change
            return true;
        }
        return false;
    }

    public boolean incompleteTodoById(Long id){
        Todo todo = todoMap.get(id);
        if(todo != null && todo.isCompleted()) {
            todo.setCompleted(false);
            todos.sort(null); // Re-sort after status change
            return true;
        }
        return false;
    }
}
