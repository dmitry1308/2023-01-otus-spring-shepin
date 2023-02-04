package ru.otus.shepin.spring.service.printService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.reportService.ReportService;

import java.io.PrintStream;

@Service("printResultService")
public class PrintResultService implements PrintService<TestResult> {
    private final ReportService<TestResult> reportService;
    private final PrintStream stream;

    public PrintResultService(ReportService<TestResult> reportService, @Value("#{T(java.lang.System).out}") PrintStream stream) {
        this.reportService = reportService;
        this.stream = stream;
    }

    public void print(TestResult data) {
        String report = reportService.formReport(data);
        stream.println(report);
    }
}
