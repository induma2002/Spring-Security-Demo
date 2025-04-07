package com.eczybytes.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/Contact")
    public String sayWelcome() {
        return "Contact Controller sayWelcome method called";
    }
}
