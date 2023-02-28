package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.exception.TestException;
import ru.otus.shepin.spring.service.io.OutputService;
import ru.otus.shepin.spring.service.pass.TestService;
import ru.otus.shepin.spring.service.person.PersonService;
import ru.otus.shepin.spring.service.report.ReportService;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class TestController implements Controller {
    private final OutputService outputService;
    private final PersonService personDataService;
    private final TestService   testService;
    private final ReportService reportService;

    private boolean isAvailablePassTest;

    @EventListener(ApplicationReadyEvent.class)
    @ShellMethod(value = "Enter user data and pass test", key = {"run-app"})
    @Override
    public void run() {
        try {
            outputService.print("\n" + "---------- Test ----------");
            enterPersonData();
            passTest();
        } catch (TestException e) {
            outputService.print(e.getMessage());
            outputService.print("Cause" + e.getCause().getMessage());
        }
    }

    @ShellMethod(value = "Enter person data", key = "ep")
    private void enterPersonData() {
        Person personData = personDataService.getPersonData();
        String reportPerson = reportService.formPersonReport(personData);
        outputService.print(reportPerson);
        isAvailablePassTest = true;
    }

    @ShellMethod(value = "Pass test", key = "pt")
    @ShellMethodAvailability("isAvailablePassTest")
    private void passTest()  {
        TestResult testResult = testService.startTest();
        String reportResult = reportService.formResultReport(testResult);
        outputService.print(reportResult);
    }

    Availability isAvailablePassTest() {
        return isAvailablePassTest ? Availability.available():Availability.unavailable("Enter person data");
    }
}