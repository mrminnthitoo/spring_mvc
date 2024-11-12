package com.minnthitoo.spring_mvc.controller.api.advice;

import com.minnthitoo.spring_mvc.controller.api.dto.ApiError;
import com.minnthitoo.spring_mvc.controller.api.dto.ErrorCodes;
import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        String error = exception.getName() + " should be type of " + exception.getRequiredType().getName();
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST,
                ErrorCodes.INVALID_PARAMETER_TYPE.toString(),
                exception.getLocalizedMessage(),
                error
        );
        return new ResponseEntity<Object>(
                apiError,
                new HttpHeaders(),
                apiError.getStatus()
        );
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception){
        String error = exception.getMessage();
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND,
                ErrorCodes.BOOK_NOT_FOUND.toString(),
                exception.getLocalizedMessage(),
                error
        );
        return new ResponseEntity<Object>(
                apiError,
                new HttpHeaders(),
                apiError.getStatus()
        );
    }

}
