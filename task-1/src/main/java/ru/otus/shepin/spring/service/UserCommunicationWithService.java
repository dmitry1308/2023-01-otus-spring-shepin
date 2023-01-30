package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCommunicationWithService {
    private ScannerService scannerManager;
    private PrintService   printService;

    public String askPersonAndGetAnswer(String question) {
        printService.print(question);
        return scannerManager.getScanner().nextLine();
    }
}