package ru.otus.shepin.spring.service.scannerService;

import java.util.Scanner;

public interface ScannerService {

    Scanner getScanner();

    void closeScanner();
}
