package ru.otus.spring.shepin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;


// url h2 консоли: http://localhost:8082/h2-console
// url базы: jdbc:h2:mem:librarydb

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
