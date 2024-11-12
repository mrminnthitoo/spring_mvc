package com.minnthitoo.spring_mvc.controller.api;

import com.minnthitoo.spring_mvc.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/books")
public interface BookApiController {

    @GetMapping()
    List<BookDto> getAllBooks();


    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found.",
                    content = @Content),
    })
    @GetMapping("/{bookId}")
    ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) throws BookNotFoundException;

    @PostMapping
    ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto book, BindingResult result) throws BeanValidationException;

    @PutMapping("/{bookId}")
    ResponseEntity<BookDto> updateBook(@PathVariable Long bookId, @Valid @RequestBody BookDto bookDto, BindingResult result) throws BookNotFoundException, BeanValidationException;

    @DeleteMapping("/{bookId}")
    ResponseEntity<BookDto> deleteBook(@PathVariable Long bookId) throws BookNotFoundException;

}
