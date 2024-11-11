package com.minnthitoo.spring_mvc.controller;

import com.minnthitoo.spring_mvc.model.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

@Slf4j
@Controller
@RequestMapping("/view-demo")
public class ViewDemoController {

    @ExceptionHandler(Exception.class)
    String getException(Exception e){
        log.info("Exception happened");
        return "view-demo/error";
    }
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

    @GetMapping("/problem")
    String problem() throws Exception {
        throw new Exception("An error occurred.");
    }

}
