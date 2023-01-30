package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.manager.ScannerManager;

@AllArgsConstructor
public class UserCommunicationWithService {
    private ScannerManager scannerManager;
    private PrintService printService;

    public String askPersonAndGetAnswer(String question) {
        printService.print(question);
        return scannerManager.getScanner().nextLine();
    }
}