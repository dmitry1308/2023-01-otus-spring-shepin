package ru.otus.shepin.spring.service.printService;

import ru.otus.shepin.spring.entity.TestResult;

public class PrintServiceImpl implements PrintService{

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
