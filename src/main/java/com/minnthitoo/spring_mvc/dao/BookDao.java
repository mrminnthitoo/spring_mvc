package com.minnthitoo.spring_mvc.dao;

import com.minnthitoo.spring_mvc.model.Book;
import com.minnthitoo.spring_mvc.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookDao{
    List<Book> getAllBooks();
    Book saveBook(BookDto book);
    Optional<Book> getBookById(Long id);
    Book updateBook(BookDto book);
    Book deleteBookById(Long id);

}
