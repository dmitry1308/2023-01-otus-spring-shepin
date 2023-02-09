package ru.otus.shepin.spring.service.processor;

import java.io.IOException;

public interface MenuCommandsProcessor {
    void showMainTitle();

    void handlePerson();

    void handleProcessTest() throws IOException;
}
