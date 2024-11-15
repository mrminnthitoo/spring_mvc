package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public BookDto saveBook(BookDto book) {
        this.books.add(book);
        return book;
    }

    @Override
    public Optional<BookDto> getBookById(Long bookId) {
        BookDto book = null;
        for (BookDto bookDto : this.books) {
            if (bookDto.getId().equals(bookId)) {
                book = bookDto;
                break;
            }
        }
        return book == null ? Optional.empty() : Optional.of(book);
    }

    @Override
    public BookDto updateBook(BookDto book) {
        BookDto bookToUpdate = this.getBookById(book.getId()).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookToUpdate;
    }

    @Override
    public BookDto deleteBook(BookDto book) {
        this.books.remove(book);
        return book;
    }
}
