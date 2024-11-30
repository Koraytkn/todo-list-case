package com.example.todo_list_sap_case.mapper;

import com.example.todo_list_sap_case.model.Todo;
import com.example.todo_list_sap_case.model.TodoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

    public static TodoDTO toDto(Todo todo) {
        return new TodoDTO(todo.getId(), todo.getTask(), todo.isCompleted());
    }

    public static Todo toEntity(TodoDTO todoDTO) {
        return new Todo(
                todoDTO.getId(),
                todoDTO.getTask(),
                todoDTO.isCompleted()
        );
    }

    public static List<TodoDTO> toDtoList(List<Todo> todos) {
        return todos.stream()
                .map(TodoMapper::toDto)
                .collect(Collectors.toList());
    }
}
