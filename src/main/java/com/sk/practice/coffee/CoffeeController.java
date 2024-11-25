package com.sk.practice.coffee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @GetMapping
    Coffee anyOne(){
        return new Coffee(1L, "random");
    }
}

record Coffee(Long id, String name){}
