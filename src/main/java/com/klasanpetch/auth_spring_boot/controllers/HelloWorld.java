package com.klasanpetch.auth_spring_boot.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/hello")
    public String greet() {
        return "Hello World";
    }
}
