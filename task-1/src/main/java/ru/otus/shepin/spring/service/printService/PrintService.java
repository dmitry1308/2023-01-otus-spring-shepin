package ru.otus.shepin.spring.service.printService;

import ru.otus.shepin.spring.entity.TestResult;

public interface PrintService {

    void print(String data) ;

    void printResult(TestResult testResult);
}
