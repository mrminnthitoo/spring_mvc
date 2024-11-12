package com.minnthitoo.spring_mvc.controller.api;

import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) throws BookNotFoundException{
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            throw new BookNotFoundException("book id " + bookId + " not found");
        }
    }

}
