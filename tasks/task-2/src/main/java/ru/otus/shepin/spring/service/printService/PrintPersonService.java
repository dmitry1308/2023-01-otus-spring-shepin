package ru.otus.shepin.spring.service.printService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.processor.ReportService;

import java.io.PrintStream;

@Service
public class PrintPersonService implements PrintService<Person> {
    private final ReportService reportService;
    private final PrintStream   stream;

    public PrintPersonService(ReportService reportService, @Value("${printStream}") PrintStream stream) {
        this.reportService = reportService;
        this.stream = stream;
    }

    public void print(Person data) {
        String report = reportService.formPersonReport(data);
        stream.println(report);
    }
}
