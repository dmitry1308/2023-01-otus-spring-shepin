package ru.otus.spring.shepin;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;


// url h2 консоли: http://localhost:8082/h2-console
// url базы: jdbc:h2:mem:librarydb

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class, args);
        Console.main(args);
    }

}
