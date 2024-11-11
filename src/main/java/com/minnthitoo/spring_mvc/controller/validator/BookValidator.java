package com.minnthitoo.spring_mvc.controller.validator;

import com.minnthitoo.spring_mvc.model.dto.BookDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BookDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // title not null, not start with capital
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required.book.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "required.book.author");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.book.description");

        BookDto book = (BookDto) target;
        if (!book.getTitle().isEmpty()){
            String firstChar = book.getTitle().charAt(0) + "";
            if (!firstChar.toUpperCase().equals(firstChar)){
                errors.rejectValue("title", "uppercase.book.title");
            }
        }
    }
}
