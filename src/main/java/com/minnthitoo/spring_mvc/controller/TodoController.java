package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.model.dto.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
@RequestMapping("todos")
public class TodoController {

    @Autowired
    RestClient restClient;

    @GetMapping
    String fetchAllTodos(Model model){
        List<TodoDto> todos = restClient.get()
                .uri("/todos")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(List.class);
        model.addAttribute("todos", todos);
        return "todos/todo.html";
    }

}
