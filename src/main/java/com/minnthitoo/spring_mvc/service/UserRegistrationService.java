package com.minnthitoo.spring_mvc.service;

import com.minnthitoo.spring_mvc.model.dto.UserDto;

public interface UserRegistrationService {
    UserDto registerUser(UserDto user) throws Exception;
}
