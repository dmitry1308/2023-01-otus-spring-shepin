package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.PersonService;
import ru.otus.shepin.spring.service.ResultPrintService;
import ru.otus.shepin.spring.manager.ScannerManager;
import ru.otus.shepin.spring.service.TestService;

@AllArgsConstructor
public class TestController {
    private ScannerManager     scannerManager;
    private PersonService      personService;
    private TestService        testService;
    private ResultPrintService resultPrintService;

    public void passTest() {
        System.out.println("\n" + "---------- Test ----------");

        Person personData = personService.getPersonData();
        System.out.println("PersonData:");
        System.out.println(personData);

        TestResult testResult = testService.startTest();
        String reportResult = resultPrintService.createReportResult(testResult);
        System.out.println(reportResult);

        scannerManager.closeScanner();
    }
}
