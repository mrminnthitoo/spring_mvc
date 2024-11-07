package com.minnthitoo.spring_mvc.service;

import com.minnthitoo.spring_mvc.model.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    void saveBook(BookDto book);
}
