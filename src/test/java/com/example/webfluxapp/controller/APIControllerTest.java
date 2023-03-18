package com.example.webfluxapp.controller;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

class APIControllerTest {

    @Test
    void shouldLog() {
        Mono.just("Reactive programming is awesome!")
                .log()
                .doOnSuccess(System.out::println)
                .subscribe();
    }

    @Test
    void zip() {
        Mono.zip(Mono.just(10), Mono.empty())
                .map((t) -> {
                    System.out.println("Inside map------->");
                    return t.getT1();
                })
                .switchIfEmpty(Mono.defer(() -> Mono.just(100)))
                .log()
                .doOnSuccess(System.out::println)
                .subscribe();
    }

    @Test
    void onErrorMap() {
        Mono.just(10)
                .map((n) -> {
                    try {
                        throw new Exception("");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).onErrorMap((e) -> {
                    System.out.println("received exception");
                    return new NullPointerException();
                })
                .onErrorResume((e) -> Mono.just(100))
                .subscribe();
    }
}