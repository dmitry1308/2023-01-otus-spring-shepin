package ru.otus.shepin.spring.service.personDataService.userCommunication;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.service.printService.PrintService;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

@AllArgsConstructor
public class UserConsoleCommunicationService implements UserCommunicationService {
    private ScannerService scannerManager;
    private PrintService   printService;

    public String askPersonAndGetAnswer(String question) {
        printService.print(question);
        return scannerManager.getScanner().nextLine();
    }
}