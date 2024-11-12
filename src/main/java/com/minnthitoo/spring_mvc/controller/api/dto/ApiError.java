package com.minnthitoo.spring_mvc.controller.api.dto;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class ApiError {
    private HttpStatusCode status;
    private String errorCode;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatusCode status, String errorCode, String message, List<String> errors){
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatusCode status, String errorCode, String message, String error){
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }
}
