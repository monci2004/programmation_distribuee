package com.example.MonsefMLSD;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class objectone {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}