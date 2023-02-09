package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.service.processor.MenuCommandsProcessor;
import ru.otus.shepin.spring.service.printService.PrintManager;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TestController implements Controller {
    private final MenuCommandsProcessor commandsProcessor;
    private final ScannerService        scannerService;
    private final PrintManager          printManager;


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
