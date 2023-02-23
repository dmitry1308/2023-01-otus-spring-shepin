package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.io.OutputService;
import ru.otus.shepin.spring.service.pass.TestService;
import ru.otus.shepin.spring.service.person.PersonService;
import ru.otus.shepin.spring.service.report.ReportService;

import java.io.IOException;

@AllArgsConstructor
@Component
public class TestController implements Controller {
    private final OutputService outputService;
    private final PersonService personDataService;
    private final TestService   testService;
    private final ReportService     reportService;

    @EventListener(ApplicationReadyEvent.class)
    @Override
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