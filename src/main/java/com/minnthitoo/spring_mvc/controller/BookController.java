package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.controller.validator.BookValidator;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(bookValidator);
    }

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

    /*
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

     */

    // save book with custom validator
    @PostMapping("/new")
    String saveBook(Model model, @Validated @ModelAttribute("book") BookDto book, BindingResult result){
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

    // edit page
    @GetMapping("edit/{bookId}")
    String editBook(@PathVariable Long bookId, Model model){
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (result.isPresent()){
            model.addAttribute("edit", true);
            model.addAttribute("book", result.get());
            return "/books/new";
        }else{
            return "/books/not-found";
        }
    }

    // update book
    @PostMapping("update")
    String updateBook(Model model, @Validated @ModelAttribute("book") BookDto book, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("edit", true);
            model.addAttribute("book", book);
            return "/books/new";
        }else{
            this.bookService.updateBook(book);
            return "redirect:/books";
        }
    }

    // delete book
    @GetMapping("delete/{bookId}")
    String deleteBook(@PathVariable Long bookId){
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (result.isPresent()){
            this.bookService.deleteBook(result.get());
            return "redirect:/books";
        }else {
            return "/books/not-found";
        }
    }

}
