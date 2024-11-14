package com.minnthitoo.spring_mvc.repository.impl;

import com.minnthitoo.spring_mvc.common.Mapper;
import com.minnthitoo.spring_mvc.model.Book;
import com.minnthitoo.spring_mvc.model.dto.BookDto;
import com.minnthitoo.spring_mvc.repository.BookDao;
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
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Mapper mapper;

    @Override
    public List<Book> getAllBook() {
        return jdbcTemplate.query("select * from book", this::mapRowToBook);
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        Book book = this.mapper.map(bookDto, Book.class);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement("insert into book (title, author, description) values (?,?,?)", new String[]{"id"});
                    ps.setString(1, book.getTitle());
                    ps.setString(2, book.getAuthor());
                    ps.setString(3, book.getDescription());
                    return ps;
                },
                keyHolder
        );
        book.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return book;
    }

    @Override
    public Optional<Book> getBookById(Long bookId) {
        List<Book> books = jdbcTemplate.query("select * from book where id=?", this::mapRowToBook, bookId);
        if (books.size() == 1){
            return Optional.of(books.getFirst());
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Book updateBook(BookDto bookDto) {
        Book book = this.mapper.map(bookDto, Book.class);
        String SQL_QUERY = "update book set title=?, author=?, description=? where id=?;";
        this.jdbcTemplate.update(SQL_QUERY, book.getTitle(), book.getAuthor(), book.getDescription(), book.getId());
        return book;
    }

    @Override
    public Book deleteBook(BookDto bookDto) {
        Book deletedBook = this.mapper.map(bookDto, Book.class);
        jdbcTemplate.update("delete from book where id=?", deletedBook.getId());
        return deletedBook;
    }

    private Book mapRowToBook(ResultSet row, int rowNum) throws SQLException{
        return new Book(row.getLong("id"), row.getString("title"), row.getString("author"), row.getString("description"));
    }
}
