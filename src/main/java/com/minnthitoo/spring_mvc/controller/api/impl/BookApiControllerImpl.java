package com.minnthitoo.spring_mvc.controller.api.impl;

import com.minnthitoo.spring_mvc.controller.api.BookApiController;
import com.minnthitoo.spring_mvc.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class BookApiControllerImpl implements BookApiController {

    @Autowired
    private BookService bookService;

    @Override
    public List<BookDto> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    @Override
    public ResponseEntity<BookDto> getBookById(@PathVariable Long bookId) throws BookNotFoundException{
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            throw new BookNotFoundException("book id " + bookId + " not found");
        }
    }

    @Override
    public ResponseEntity<BookDto> createBook(@Valid BookDto bookDto, BindingResult result) throws BeanValidationException {

        if (!result.hasErrors()){
            BookDto createdBook = this.bookService.saveBook(bookDto);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(createdBook);
        }else {
            throw new BeanValidationException(result.getAllErrors());
        }

    }

    @Override
    public ResponseEntity<BookDto> updateBook(Long bookId, BookDto bookDto, BindingResult bindingResult) throws BookNotFoundException, BeanValidationException {
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (!bindingResult.hasErrors()){
            if (result.isPresent()){
                bookDto.setId(bookId);
                BookDto updatedBook = this.bookService.updateBook(bookDto);
                return ResponseEntity.ok(updatedBook);
            }else {
                throw new BookNotFoundException("Book id " + bookId + "not found.");
            }
        } else {
            throw new BeanValidationException(bindingResult.getAllErrors());
        }
    }

    @Override
    public ResponseEntity<BookDto> deleteBook(Long bookId) throws BookNotFoundException {
        Optional<BookDto> result = this.bookService.getBookById(bookId);
        if (result.isPresent()){
            BookDto deletedBook = this.bookService.deleteBook(result.get());
            return ResponseEntity.ok(deletedBook);
        }else {
            throw new BookNotFoundException("Book id " + bookId + " not found.");
        }
    }

}
