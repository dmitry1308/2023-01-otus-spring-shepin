package ru.otus.shepin.spring.service.reportService;

import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;

public interface ReportService {
    String formResultReport(TestResult data);
    String formPersonReport(Person data);
}
