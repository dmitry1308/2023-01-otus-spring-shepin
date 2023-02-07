package ru.otus.shepin.spring.service.printService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class PrintStringService implements PrintService<String> {
    @Value("${printStream}")
    private PrintStream stream;

    public void print(String data) {
        stream.println(data);
    }
}
