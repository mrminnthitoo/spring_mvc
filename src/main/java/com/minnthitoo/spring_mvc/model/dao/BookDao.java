package com.minnthitoo.spring_mvc.model.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDao {
    private Long id;

    @NotNull
    @NotBlank(message = "name can't be blank")
    @Size(min = 3, max = 100, message = "name must be between 3 to 100")
    private String name;

    @NotNull
    @NotBlank(message = "author can't be blank")
    @Size(min = 3, max = 100, message = "author must be between 3 to 100")
    private String author;

    @NotNull
    @NotBlank(message = "description can't be blank")
    @Size(min = 3, max = 100, message = "description must be between 3 to 100")
    private String description;
}
