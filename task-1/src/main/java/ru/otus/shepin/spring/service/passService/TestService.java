package ru.otus.shepin.spring.service.passService;

import ru.otus.shepin.spring.entity.TestResult;

import java.io.IOException;


public interface TestService {
    TestResult startTest() throws IOException;
}
