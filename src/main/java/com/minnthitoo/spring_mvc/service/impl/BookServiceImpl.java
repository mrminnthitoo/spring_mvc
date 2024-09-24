package com.minnthitoo.spring_mvc.service.impl;

import com.minnthitoo.spring_mvc.common.Mapper;
import com.minnthitoo.spring_mvc.dao.BookDao;
import com.minnthitoo.spring_mvc.model.Book;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    Mapper mapper;

    List<BookDto> books = new ArrayList<BookDto>();

    public BookServiceImpl() {
        this.books.add(new BookDto(1L, "Title 1", "Author 1", "Description 1"));
        this.books.add(new BookDto(2L, "Title 2", "Author 2", "Description 2"));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = this.bookDao.getAllBooks();
        List<BookDto> bookList = mapper.mapList(books, BookDto.class);
        return bookList;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book bookDao = this.bookDao.saveBook(bookDto);
        BookDto book = this.mapper.getModelMapper().map(bookDao, BookDto.class);
        return book;
    }

    @Override
    public Optional<BookDto> getBookById(Long id) {
        Optional<Book> book = this.bookDao.getBookById(id);
        if (book.isPresent()) {
            BookDto bookDto = this.mapper.getModelMapper().map(book.get(), BookDto.class);
            return Optional.of(bookDto);
        }else{
            return Optional.empty();
        }
    }

    @Override
    public BookDto updateBook(BookDto book) {
        Book bookDao = this.bookDao.updateBook(book);
        BookDto bookDto = this.mapper.getModelMapper().map(bookDao, BookDto.class);
        return bookDto;
    }

    @Override
    public BookDto deleteBook(BookDto book) {
        Book bookDao = this.bookDao.deleteBookById(book.getId());
        BookDto bookDto = this.mapper.getModelMapper().map(bookDao, BookDto.class);
        return bookDto;
    }

}
