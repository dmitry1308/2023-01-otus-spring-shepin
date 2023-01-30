package ru.otus.shepin.spring.service;

import ru.otus.shepin.spring.entity.TestResult;

public class ResultPrintService {

    public void printResult(TestResult testResult) {
        print(createReportResult(testResult));
    }

    public String createReportResult(TestResult testResult) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("-------------------------------------------------------------------------------------\n");
        builder.append("Test Result:");
        builder.append("\n");
        builder.append(testResult);
        return builder.toString();
    }

    private void print(String reportResult) {
        System.out.println(reportResult);
    }
}
