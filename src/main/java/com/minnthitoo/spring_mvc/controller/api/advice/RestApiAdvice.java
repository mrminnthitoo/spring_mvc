package com.minnthitoo.spring_mvc.controller.api.advice;

import com.minnthitoo.spring_mvc.controller.api.dto.ApiError;
import com.minnthitoo.spring_mvc.controller.api.dto.ErrorCodes;
import com.minnthitoo.spring_mvc.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_mvc.controller.api.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestApiAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_PARAMETER_TYPE.toString(), ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> bookNotFound(BookNotFoundException e) {
        String error = e.getMessage();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ErrorCodes.BOOK_NOT_FOUND.toString(), e.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(BeanValidationException.class)
    public ResponseEntity<Object> handleBeanValidationException(BeanValidationException e) {
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getErrors()){
            errors.add(error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCodes.BEAN_VALIDATION_ERROR.toString(), e.getLocalizedMessage(), errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
