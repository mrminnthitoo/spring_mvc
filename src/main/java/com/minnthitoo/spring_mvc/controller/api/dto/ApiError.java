package com.minnthitoo.spring_mvc.controller.api.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {
    private HttpStatus status;
    private String errorCode;
    private String message;
    private List<String> errors = new ArrayList<>();

    public ApiError(HttpStatus status, String errorCode, String message, List<String> errors) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String errorCode, String message, String error) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors.add(error);
    }

}
