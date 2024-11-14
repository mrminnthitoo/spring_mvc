package com.minnthitoo.spring_mvc.repository;

import com.minnthitoo.spring_mvc.model.Book;
import com.minnthitoo.spring_mvc.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao{
    List<Book> getAllBook();
    Book saveBook(BookDto bookDto);
    Optional<Book> getBookById(Long bookId);
    Book updateBook(BookDto bookDto);
    Book deleteBook(BookDto bookDto);
}
