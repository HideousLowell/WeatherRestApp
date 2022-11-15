package ru.eshakin.WeatherRestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @GetMapping("/sayHello")
    public String sayHello() {
         return "Hello world!";
    }
}
