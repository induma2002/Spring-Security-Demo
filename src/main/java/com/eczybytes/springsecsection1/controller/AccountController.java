package com.eczybytes.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String sayWelcome() {
        return "Account Controller My Security Application";
    }
}
