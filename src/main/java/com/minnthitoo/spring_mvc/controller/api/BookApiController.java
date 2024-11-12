package com.minnthitoo.spring_mvc.controller.api;

import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/books")
public class BookApiController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    List<BookDto> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) throws BookNotFoundException{
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            throw new BookNotFoundException("book id " + bookId + " not found");
        }
    }

}
