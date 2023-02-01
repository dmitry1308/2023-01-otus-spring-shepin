package ru.otus.shepin.spring.service.printService;

import lombok.RequiredArgsConstructor;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.reportService.ReportService;

@RequiredArgsConstructor
public class PrintResultService implements PrintService<TestResult>{
    ReportService<TestResult> reportService;

    public PrintResultService(ReportService<TestResult> reportService) {
        this.reportService = reportService;
    }

    public void print(TestResult data) {
        String report = reportService.formReport(data);
        System.out.println(report);
    }
}
