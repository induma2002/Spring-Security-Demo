package com.eczybytes.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notes")
    public String sayWelcome() {
        return "notes Spring Boot Security Application";
    }
}
