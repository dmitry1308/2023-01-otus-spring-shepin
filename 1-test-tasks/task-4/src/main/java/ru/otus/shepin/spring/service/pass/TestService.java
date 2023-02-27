package ru.otus.shepin.spring.service.pass;

import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.exception.TestException;

import java.io.IOException;


public interface TestService {
    TestResult startTest() throws IOException, TestException;
}
