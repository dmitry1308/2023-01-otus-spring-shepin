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

    public IOServiceStreams(@Value("${printStream}") PrintStream printStream,
                            @Value("${enterStream}") InputStream inputStream) {
        this.output = printStream;
        input = new Scanner(inputStream, StandardCharsets.UTF_8);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        print(prompt);
        return input.nextLine();
    }

    @Override
    public void print(String printLine) {
        output.println(printLine);
    }
}
