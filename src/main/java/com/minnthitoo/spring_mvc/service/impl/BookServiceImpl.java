package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.model.dao.BookDao;
import com.minnthitoo.spring_mvc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    List<BookDao> books = new ArrayList<BookDao>();

    public BookServiceImpl() {
        this.books.add(new BookDao(1L, "Title 1", "Author 1", "Description 1"));
        this.books.add(new BookDao(2L, "Title 2", "Author 2", "Description 2"));
    }

    @Override
    public List<BookDao> getAllBooks() {
        return this.books;
    }

    @Override
    public void addBook(BookDao book) {
        this.books.add(book);
    }

    @Override
    public Optional<BookDao> getBookById(Long id) {
        BookDao book = null;
        for (BookDao bookDao : this.books) {
            if (bookDao.getId().equals(id)) {
                book = bookDao;
                break;
            }
        }
        return book != null ? Optional.of(book) : Optional.empty();
    }

    @Override
    public BookDao updateBook(BookDao book) {
        for (BookDao bookDao : this.books) {
            if (bookDao.getId().equals(book.getId())) {
                bookDao.setName(book.getName());
                bookDao.setAuthor(book.getAuthor());
                bookDao.setDescription(book.getDescription());
            }
        }
        return book;
    }

    @Override
    public BookDao deleteBook(BookDao book) {
        for (BookDao bookDao : this.books) {
            if (bookDao.getId().equals(book.getId())) {
                this.books.remove(bookDao);
                break;
            }
        }
        return book;
    }

}
