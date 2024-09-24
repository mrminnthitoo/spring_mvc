package com.minnthitoo.spring_mvc.dao.impl;

import com.minnthitoo.spring_mvc.common.Mapper;
import com.minnthitoo.spring_mvc.model.Book;
import com.minnthitoo.spring_mvc.dao.BookDao;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @Autowired
    Mapper mapper;

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT id, title, author, description FROM book;", this::mapRowToBook);
    }

    private Book mapRowToBook(ResultSet row, int rowNum) throws SQLException {
        return new Book(row.getLong("id"), row.getString("title"), row.getString("author"), row.getString("description"));
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        String INSERT_SQL = "INSERT INTO book (title, author, description) VALUES (?, ?, ?);";

        Book book = this.mapper.getModelMapper().map(bookDto, Book.class);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                    preparedStatement.setString(1, book.getTitle());
                    preparedStatement.setString(2, book.getAuthor());
                    preparedStatement.setString(3, book.getDescription());
                    return preparedStatement;
                },
                keyHolder
        );
        book.setId(keyHolder.getKey().longValue());
        return book;
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        List<Book> books = jdbcTemplate.query("SELECT id, title, author, description FROM book where id=?", this::mapRowToBook, id);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

    @Override
    public Book updateBook(BookDto book) {
        Book bookToUpdate = this.mapper.getModelMapper().map(book, Book.class);
        String SQL = "UPDATE book set title = ?, author = ?, description = ? WHERE id = ?;";

        this.jdbcTemplate.update(SQL, bookToUpdate.getTitle(), bookToUpdate.getAuthor(), bookToUpdate.getDescription(), bookToUpdate.getId());
        return bookToUpdate;
    }

    @Override
    public Book deleteBookById(Long id) {
        Optional<Book> book = this.getBookById(id);
        String SQL = "DELETE FROM book WHERE id = ?;";
        this.jdbcTemplate.update(SQL, id);
        return book.get();
    }
}
