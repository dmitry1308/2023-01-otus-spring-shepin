package ru.otus.shepin.spring;

import java.io.IOException;

public interface MenuCommandsProcessor {
    void showMainTitle();

    void handlePerson();

    void handleProcessTest() throws IOException;
}
