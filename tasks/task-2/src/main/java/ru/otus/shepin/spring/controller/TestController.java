package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.service.ioService.OutputService;
import ru.otus.shepin.spring.service.processor.MenuCommandsProcessor;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TestController implements Controller {
    private final MenuCommandsProcessor commandsProcessor;
    private final OutputService         outputService;

    public void run() {
        try {
            commandsProcessor.showMainTitle();
            commandsProcessor.handlePerson();
            commandsProcessor.handleProcessTest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            outputService.outputString(e.getMessage());
        }
    }
}
