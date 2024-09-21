package com.minnthitoo.spring_mvc.controller.api;

import com.minnthitoo.spring_mvc.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import com.minnthitoo.spring_mvc.model.dao.BookDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
public interface BookApi {

    @GetMapping("books")
    List<BookDao> getAllBook();

    @PostMapping("books")
    ResponseEntity<BookDao> createBook(@Valid @RequestBody BookDao book, BindingResult bindingResult) throws BeanValidationException;

    @Operation(summary = "get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a book",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "book not found",
                    content = @Content),
    })
    @GetMapping("/books/{bookId}")
    ResponseEntity<BookDao> getBookById(@PathVariable("bookId") Long bookId) throws BookNotFoundException;

    @PutMapping("books/{bookId}")
    ResponseEntity<BookDao> updateBook(@PathVariable("bookId") Long bookId, @Valid @RequestBody BookDao book, BindingResult bindingResult) throws BookNotFoundException, BeanValidationException;

    @DeleteMapping("books/{bookId}")
    ResponseEntity<BookDao> deleteBook(@PathVariable("bookId") Long bookId) throws BookNotFoundException;

}
