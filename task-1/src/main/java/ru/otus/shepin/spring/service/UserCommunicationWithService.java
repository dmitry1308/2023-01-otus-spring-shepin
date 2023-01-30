package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCommunicationWithService {
    private ScannerManager scannerManager;

    public String askPersonAndGetAnswer(String question) {
        System.out.println(question);
        return scannerManager.getScanner().nextLine();
    }
}