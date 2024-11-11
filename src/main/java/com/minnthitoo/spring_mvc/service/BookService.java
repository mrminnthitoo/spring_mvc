package com.minnthitoo.spring_mvc.service;

import com.minnthitoo.spring_mvc.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();
    void saveBook(BookDto book);
    Optional<BookDto> getBookById(Long bookId);
    void updateBook(BookDto book);
    void deleteBook(BookDto book);
}
