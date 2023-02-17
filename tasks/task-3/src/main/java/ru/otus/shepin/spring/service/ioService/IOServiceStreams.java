package ru.otus.shepin.spring.service.ioService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class IOServiceStreams implements IOService {
    private final PrintStream output;
    private final Scanner     input;

    public IOServiceStreams(@Value("${stream.output}") PrintStream output,
                            @Value("${stream.input}") InputStream input) {
        this.output = output;
        this.input = new Scanner(input, StandardCharsets.UTF_8);
    }

    @Override
    public String readLine() {
        return input.nextLine();
    }

    @Override
    public void print(String printLine) {
        output.println(printLine);
    }
}
