package com.minnthitoo.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {

    @GetMapping("/login")
    String loginForm(){
        return "auth/login";
    }

    @GetMapping("/logout")
    String logoutPage(){
        return "auth/logout";
    }

}
