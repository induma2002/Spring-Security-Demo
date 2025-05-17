package com.eczybytes.springsecsection1.controller;

import com.eczybytes.springsecsection1.model.Customer;
import com.eczybytes.springsecsection1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        try {
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer registered successfully" + savedCustomer.getId());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer registration failed");
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred " + e.getMessage());
        }

    }
}
