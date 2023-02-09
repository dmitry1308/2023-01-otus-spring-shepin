package ru.otus.shepin.spring.service.personDataService.userCommunication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.service.printService.PrintService;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

@Service
@Getter
@AllArgsConstructor
public class UserConsoleCommunicationService implements UserCommunicationService {
    private final ScannerService       scannerManager;
    private final PrintService<String> printService;


    public String askPersonAndGetAnswer(String question) {
        printService.print(question);
        return scannerManager.getScanner().nextLine();
    }
}