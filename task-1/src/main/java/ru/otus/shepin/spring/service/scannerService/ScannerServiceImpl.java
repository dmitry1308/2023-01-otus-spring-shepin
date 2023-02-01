package ru.otus.shepin.spring.service.scannerService;

import java.util.Scanner;

public class ScannerServiceImpl implements ScannerService{
    private Scanner scanner;

    private ScannerServiceImpl() {}

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
