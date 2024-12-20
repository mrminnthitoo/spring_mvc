package com.minnthitoo.spring_mvc.model.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookDto {

    private Long id;

    @NotBlank(message = "{required.book.title}")
    @Size(min = 3, max = 100, message = "{size.book.title}")
    private String title;

    @NotBlank(message = "{required.book.author}")
    @Size(min = 3, max = 100, message = "{size.book.author}")
    private String author;

    @NotBlank(message = "{required.book.description}")
    @Size(min = 3, max = 100, message = "{size.book.description}")
    private String description;

}
