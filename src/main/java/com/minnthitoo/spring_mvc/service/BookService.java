package com.minnthitoo.spring_mvc.service;

import com.minnthitoo.spring_mvc.model.dao.BookDao;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDao> getAllBooks();
    void addBook(BookDao book);
    Optional<BookDao> getBookById(Long id);
    BookDao updateBook(BookDao book);
    BookDao deleteBook(BookDao book);
}
