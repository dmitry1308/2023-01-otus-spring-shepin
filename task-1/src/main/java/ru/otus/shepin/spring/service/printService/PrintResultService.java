package ru.otus.shepin.spring.service.printService;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.reportService.ReportService;

import java.io.PrintStream;

@AllArgsConstructor
public class PrintResultService implements PrintService<TestResult> {
   private ReportService<TestResult> reportService;
    private PrintStream stream;

    public void print(TestResult data) {
        String report = reportService.formReport(data);
        stream.println(report);
    }
}
