package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.model.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    String getAllBooks(Model model){
        List<BookDto> books = this.bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books/books";
    }

}
