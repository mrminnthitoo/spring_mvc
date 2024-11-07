package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.model.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.RouteMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
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

    // new book form
    @GetMapping("/new")
    String newBook(Model model){
        BookDto book = new BookDto();
        book.setTitle("How to win friends & influence people");
        model.addAttribute("book", book);
        return "books/new";
    }

    // save book
    @PostMapping("/new")
    String saveBook(Model model, @Valid @ModelAttribute("book") BookDto book, BindingResult result){
        if (!result.hasErrors()){
            this.bookService.saveBook(book);
            return "redirect:/books";

        }else {
            for (ObjectError error : result.getAllErrors()){
                log.info("Error {}", error.getDefaultMessage());
            }
            model.addAttribute("book", book);
            return "books/new";
        }
    }

}
