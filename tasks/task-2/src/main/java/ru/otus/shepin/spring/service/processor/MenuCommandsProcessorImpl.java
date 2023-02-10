package ru.otus.shepin.spring.service.processor;

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
public class MenuCommandsProcessorImpl implements MenuCommandsProcessor {
    private final PersonDataService personDataService;
    private final TestService       testService;
    private final OutputService     outputService;
    private final ReportService     reportService;

    @Override
    public void showMainTitle() {
        outputService.print("\n" + "---------- Test ----------");
    }

    @Override
    public void handlePerson() {
        Person personData = personDataService.getPersonData();
        String report = reportService.formPersonReport(personData);
        outputService.print(report);
    }

    @Override
    public void handleProcessTest() throws IOException {
        TestResult testResult = testService.startTest();
        String report = reportService.formResultReport(testResult);
        outputService.print(report);
    }
}







