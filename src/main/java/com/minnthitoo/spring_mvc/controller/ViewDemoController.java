package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.model.BookDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Controller
@RequestMapping("/view-demo")
public class ViewDemoController {

    @GetMapping
    String viewDemo(Model model){
        model.addAttribute("message", "Hello From Model");
        model.addAttribute("today", Calendar.getInstance());
        return "viewDemo";
    }

    @GetMapping("/object")
    String demoObject(Model model){
        BookDto bookDto = new BookDto(1L, "Title 1", "Author 1", "Description 1");
        model.addAttribute("book", bookDto);
        return "view-demo/books";
    }

}
