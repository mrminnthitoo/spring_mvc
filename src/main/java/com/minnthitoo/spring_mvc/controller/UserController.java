package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.model.dto.UserDto;
import com.minnthitoo.spring_mvc.service.UserRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @GetMapping("/register")
    public String register(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "auth/register";
    }

    @PostMapping("/register")
    public String saveRegisteredUser(Model model, @Valid @ModelAttribute("user") UserDto user, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("user", user);
        }else{

            try {
                this.userRegistrationService.registerUser(user);
            }catch (Exception exception){
                model.addAttribute("message", "user already existed.");
                model.addAttribute("user", user);
                return "auth/register";
            }

            model.addAttribute("message", "user has been registered.");
            model.addAttribute("user", new UserDto());
        }
        return "auth/register";
    }

}
