package com.example.webfluxapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class APIController {
    private final WebClient webClient;

    APIController() {
        this.webClient = WebClient.builder().build();
    }

    @GetMapping("/api")
    public Mono<Student> api() {
        return webClient
                .get()
                .uri("http://localhost:8081")
                .retrieve()
                .bodyToMono(Student.class);
    }
}

class Student {
    public String name;
    public Integer age;
}
