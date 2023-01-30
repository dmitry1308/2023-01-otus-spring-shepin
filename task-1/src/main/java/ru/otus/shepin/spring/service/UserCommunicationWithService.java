package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.manager.ScannerManager;

@AllArgsConstructor
public class UserCommunicationWithService {
    private ScannerManager scannerManager;

    public String askPersonAndGetAnswer(String question) {
        System.out.println(question);
        return scannerManager.getScanner().nextLine();
    }
}