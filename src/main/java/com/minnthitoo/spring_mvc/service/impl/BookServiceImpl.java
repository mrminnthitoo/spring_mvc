package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.common.Mapper;
import com.minnthitoo.spring_mvc.model.Book;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.repository.BookDao;
import com.minnthitoo.spring_mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private Mapper mapper;

    private List<BookDto> books = new ArrayList<>();

    public BookServiceImpl(){
        this.books.add(new BookDto(1L, "Book 1", "Author 1", "Description 1"));
        this.books.add(new BookDto(2L, "Book 2", "Author 2", "Description 2"));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = this.bookDao.getAllBook();
        return this.mapper.mapList(books, BookDto.class);
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book savedBook = this.bookDao.saveBook(bookDto);
        return this.mapper.map(savedBook, BookDto.class);
    }

    @Override
    public Optional<BookDto> getBookById(Long bookId) {
        Optional<Book> book = this.bookDao.getBookById(bookId);
        if (book.isPresent()){
            BookDto bookDto = this.mapper.map(book.get(), BookDto.class);
            return Optional.of(bookDto);
        }else {
            return Optional.empty();
        }
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book updatedBook = this.bookDao.updateBook(bookDto);
        return this.mapper.map(updatedBook, BookDto.class);
    }

    @Override
    public BookDto deleteBook(BookDto bookDto) {
        Book deletedBook = this.bookDao.deleteBook(bookDto);
        return this.mapper.map(deletedBook, BookDto.class);
    }
}
