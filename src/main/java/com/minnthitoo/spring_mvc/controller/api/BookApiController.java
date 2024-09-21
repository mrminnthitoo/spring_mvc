//package com.minnthitoo.spring_mvc.controller.api;
//
//import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
//import com.minnthitoo.spring_mvc.model.dao.BookDao;
//import com.minnthitoo.spring_mvc.service.BookService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@RestController
//@RequestMapping("/api")
//public class BookApiController {
//
//    @Autowired
//    BookService bookService;
//
//    @GetMapping("books")
//    public ResponseEntity<List<BookDao>> getAllBooks() {
//        log.info("Get /api/books");
//        return ResponseEntity.ok(bookService.getAllBooks());
//    }
//
//    @Operation(summary = "Get a book by its id")
//    @GetMapping("books/{bookId}")
//    public ResponseEntity<BookDao> getBookById(@PathVariable("bookId") Long bookId) throws BookNotFoundException {
//        Optional<BookDao> book = bookService.getBookById(bookId);
//        if (book.isPresent()) {
//            return ResponseEntity.ok(book.get());
//        }else {
//            throw new BookNotFoundException("Book id " + bookId + " not found");
//        }
//    }
//
//}
