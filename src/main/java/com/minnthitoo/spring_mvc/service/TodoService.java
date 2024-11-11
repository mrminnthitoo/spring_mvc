package com.minnthitoo.spring_mvc.service;

import com.minnthitoo.spring_mvc.model.dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodos();
}
