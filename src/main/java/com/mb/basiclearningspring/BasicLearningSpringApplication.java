package com.mb.basiclearningspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class BasicLearningSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicLearningSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner startup(){
        return args -> {

        };
    }

}
