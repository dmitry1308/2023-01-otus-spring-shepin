package ru.otus.shepin.spring.service;

import ru.otus.shepin.spring.entity.TestResult;

public class PrintService {

    public void print(String data) {
        System.out.println(data);
    }

    public void printResult(TestResult testResult) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("-------------------------------------------------------------------------------------\n");
        builder.append("Test Result:");
        builder.append("\n");
        builder.append(testResult);
        String result = builder.toString();
        print(result);
    }
}
