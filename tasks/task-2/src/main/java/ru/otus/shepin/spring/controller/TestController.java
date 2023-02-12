package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.ioService.OutputService;
import ru.otus.shepin.spring.service.passService.TestService;
import ru.otus.shepin.spring.service.personDataService.PersonDataService;
import ru.otus.shepin.spring.service.reportService.ReportService;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TestController implements Controller {
    private final OutputService     outputService;
    private final PersonDataService personDataService;
    private final TestService       testService;
    private final ReportService     reportService;

    public void run() {
        try {
            outputService.print("\n" + "---------- Test ----------");

            Person personData = personDataService.getPersonData();
            String reportPerson = reportService.formPersonReport(personData);
            outputService.print(reportPerson);

            TestResult testResult = testService.startTest();
            String reportResult = reportService.formResultReport(testResult);
            outputService.print(reportResult);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            outputService.print(e.getMessage());
        }
    }
}