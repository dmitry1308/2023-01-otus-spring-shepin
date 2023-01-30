package ru.otus.shepin.spring.service;

import java.util.Scanner;

public class ScannerService {
    private Scanner scanner;

    private ScannerService() {}

    public Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in, "UTF-8");
        }
        return scanner;
    }


    public void closeScanner() {
        scanner.close();
    }
}
