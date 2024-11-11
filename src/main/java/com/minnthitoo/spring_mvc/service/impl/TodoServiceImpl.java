package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.model.dto.TodoDto;
import com.minnthitoo.spring_mvc.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Override
    public List<TodoDto> getAllTodos() {
        List<TodoDto> todos = new ArrayList<>();
        todos.add(new TodoDto(1L, 1L, "Title 1", false));
        todos.add(new TodoDto(2L, 1L, "Title 2", true));
        todos.add(new TodoDto(3L, 1L, "Title 3", false));
        todos.add(new TodoDto(4L, 1L, "Title 4", true));
        todos.add(new TodoDto(5L, 1L, "Title 5", false));
        return todos;
    }
}
