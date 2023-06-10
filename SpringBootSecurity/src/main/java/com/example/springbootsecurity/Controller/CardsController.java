package com.example.springbootsecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CardsController {
    @GetMapping("/myCards")
    public String getCardDetails(){
        return "my cards";
    }



}
