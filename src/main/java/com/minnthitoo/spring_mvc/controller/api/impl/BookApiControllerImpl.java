package com.minnthitoo.spring_mvc.controller.api.impl;

import com.minnthitoo.spring_mvc.controller.api.BookApi;
import com.minnthitoo.spring_mvc.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class BookApiControllerImpl implements BookApi {

    @Autowired
    private BookService bookService;

    @Override
    public List<BookDto> getAllBook() {
        return this.bookService.getAllBooks();
    }

    @Override
    public ResponseEntity<BookDto> getBookById(Long bookId) throws BookNotFoundException {
        Optional<BookDto> book = this.bookService.getBookById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }else{
            throw new BookNotFoundException("Book not found");
        }
    }

    @Override
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto book, BindingResult bindingResult) throws BeanValidationException {



        if (!bindingResult.hasErrors()){
            BookDto bookDto = this.bookService.addBook(book);
            log.info(bookDto.toString());
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(bookDto);
        }else{
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> errors = new HashMap<>();
            fieldErrors.forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            log.info(errors.toString());
            throw new BeanValidationException(bindingResult.getAllErrors());
        }
    }

    @Override
    public ResponseEntity<BookDto> updateBook(@PathVariable("bookId") Long bookId, @Valid @RequestBody BookDto book, BindingResult bindingResult) throws BookNotFoundException, BeanValidationException {
        if (bindingResult.hasErrors()){
            throw new BeanValidationException(bindingResult.getAllErrors());
        }else {
            Optional<BookDto> bookDao = this.bookService.getBookById(bookId);
            if (bookDao.isPresent()) {
                BookDto bookDto = this.bookService.updateBook(book);
                return ResponseEntity.ok(bookDto);
            }else {
                throw new BookNotFoundException("Book not found");
            }
        }
    }

    @Override
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
        Optional<BookDto> book = this.bookService.getBookById(bookId);
        if (book.isPresent()) {
            this.bookService.deleteBook(book.get());
            return ResponseEntity.ok(book.get());
        }else {
            throw new BookNotFoundException("Book not found");
        }
    }

}
