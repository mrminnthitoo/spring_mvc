package com.minnthitoo.spring_mvc.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDto {

    private Long id;

    @NotBlank(message = "{required.user.username}")
    private String username;

    @NotBlank(message = "{required.user.password}")
    private String password;

}
