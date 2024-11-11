package com.minnthitoo.spring_mvc.controller.api;

import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class BookApiController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    List<BookDto> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    // query demo (query variable and query from url name must the same)
    @GetMapping("/books/search")
    List<BookDto> getAllBooks(@RequestParam String query){
        log.info("API Query {}", query);
        return this.bookService.getAllBooks();
    }

}
