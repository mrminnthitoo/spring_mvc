package com.minnthitoo.spring_mvc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoDto {
    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;
}
