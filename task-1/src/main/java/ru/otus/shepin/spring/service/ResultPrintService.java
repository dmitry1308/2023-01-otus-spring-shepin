package ru.otus.shepin.spring.service;

import ru.otus.shepin.spring.entity.TestResult;

public class ResultPrintService {
    public String createReportResult(TestResult testResult) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("-------------------------------------------------------------------------------------\n");
        builder.append("Test Result:");
        builder.append("\n");
        builder.append(testResult);
       return builder.toString();
    }
}
