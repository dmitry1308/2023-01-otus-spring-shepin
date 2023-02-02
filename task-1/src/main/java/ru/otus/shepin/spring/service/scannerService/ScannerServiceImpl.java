package ru.otus.shepin.spring.service.scannerService;

import java.io.InputStream;
import java.util.Scanner;


public class ScannerServiceImpl implements ScannerService {
    private Scanner scanner;
    private final InputStream stream;

    private ScannerServiceImpl(InputStream stream) {
        this.stream = stream;
    }

    public Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(stream, "UTF-8");
        }
        return scanner;
    }


    public void closeScanner() {
        scanner.close();
    }
}
