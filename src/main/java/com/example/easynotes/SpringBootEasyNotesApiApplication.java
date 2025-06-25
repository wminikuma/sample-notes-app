package com.example.easynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootEasyNotesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEasyNotesApiApplication.class, args);
    }

}
