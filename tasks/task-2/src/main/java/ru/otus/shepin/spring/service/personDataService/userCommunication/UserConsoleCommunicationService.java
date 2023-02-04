package ru.otus.shepin.spring.service.personDataService.userCommunication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.service.printService.PrintService;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

@Service
@Getter
public class UserConsoleCommunicationService implements UserCommunicationService {
    private ScannerService scannerManager;

    private PrintService printService;

    public UserConsoleCommunicationService(ScannerService scannerManager,@Qualifier("printStringService") PrintService printService) {
        this.scannerManager = scannerManager;
        this.printService = printService;
    }

    public String askPersonAndGetAnswer(String question) {
        printService.print(question);
        return scannerManager.getScanner().nextLine();
    }
}