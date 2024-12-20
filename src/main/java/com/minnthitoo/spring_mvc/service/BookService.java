package com.minnthitoo.spring_mvc.service;

import com.minnthitoo.spring_mvc.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto saveBook(BookDto book);
    Optional<BookDto> getBookById(Long bookId);
    BookDto updateBook(BookDto book);
    BookDto deleteBook(BookDto book);
}
