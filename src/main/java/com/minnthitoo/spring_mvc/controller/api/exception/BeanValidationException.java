package com.minnthitoo.spring_mvc.controller.api.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BeanValidationException extends Exception{

    List<ObjectError> errors;

    public BeanValidationException(List<ObjectError> errors){
        this.errors = errors;
    }

}
