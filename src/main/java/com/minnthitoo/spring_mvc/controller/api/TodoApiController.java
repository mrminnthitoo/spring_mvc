package com.minnthitoo.spring_mvc.controller.api;

import com.minnthitoo.spring_mvc.model.dto.TodoDto;
import com.minnthitoo.spring_mvc.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class TodoApiController {

    @Autowired
    TodoService todoService;

    @Autowired
    RestClient restClient;

    @GetMapping("todos")
    List<TodoDto> getAllTodos(){
        log.info("/api/todos Thread name {}", Thread.currentThread().getName());
        try{
//            Thread.sleep(2_000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.todoService.getAllTodos();
    }

    @GetMapping("todos/heavy")
    List<TodoDto> getAllTodosHeavy(){
        log.info("/api/todos getTodosHeavy");
        List<TodoDto> todos = restClient.get()
                .uri("/todos")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(List.class);
        return todos;
    }

}
