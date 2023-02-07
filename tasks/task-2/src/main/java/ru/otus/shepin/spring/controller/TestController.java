package ru.otus.shepin.spring.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.MenuCommandsProcessor;
import ru.otus.shepin.spring.service.printService.PrintManager;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

import java.io.IOException;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestController implements Controller {
    MenuCommandsProcessor commandsProcessor;
    ScannerService        scannerService;
    PrintManager          printManager;


    public void run() {
        try {
            commandsProcessor.showMainTitle();
            commandsProcessor.handlePerson();
            commandsProcessor.handleProcessTest();

            scannerService.closeScanner();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            printManager.print(e.getMessage());
        }
    }
}
