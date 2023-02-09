package ru.otus.shepin.spring.service.printService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class PrintStringService implements PrintService<String> {
    private final PrintStream stream;

    public PrintStringService(@Value("${printStream}") PrintStream stream) {
        this.stream = stream;
    }

    public void print(String data) {
        stream.println(data);
    }
}
