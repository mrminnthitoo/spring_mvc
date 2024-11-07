package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.model.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private List<BookDto> books = new ArrayList<>();

    public BookServiceImpl(){
        this.books.add(new BookDto(1L, "Book 1", "Author 1", "Description 1"));
        this.books.add(new BookDto(2L, "Book 2", "Author 2", "Description 2"));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return this.books;
    }
}
