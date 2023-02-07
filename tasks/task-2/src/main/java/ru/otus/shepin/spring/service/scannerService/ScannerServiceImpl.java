package ru.otus.shepin.spring.service.scannerService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class ScannerServiceImpl implements ScannerService {
    private       Scanner     scanner;
    private final InputStream stream;

    private ScannerServiceImpl(@Value("${enterStream}") InputStream stream) {
        this.stream = stream;
    }

    public Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(stream, StandardCharsets.UTF_8);
        }
        return scanner;
    }

    public void closeScanner() {
        scanner.close();
    }
}
