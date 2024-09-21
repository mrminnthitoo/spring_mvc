package com.minnthitoo.spring_mvc.controller.api.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}
