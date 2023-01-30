package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.PersonService;
import ru.otus.shepin.spring.service.PrintService;
import ru.otus.shepin.spring.manager.ScannerManager;
import ru.otus.shepin.spring.service.TestService;

@AllArgsConstructor
public class TestController {
    private ScannerManager scannerManager;
    private PersonService  personService;
    private TestService    testService;
    private PrintService   resultPrintService;

    public void passTest() {
        resultPrintService.print("\n" + "---------- Test ----------");

        Person personData = personService.getPersonData();
        resultPrintService.print(personData.toString());

        TestResult testResult = testService.startTest();
        resultPrintService.printResult(testResult);


        scannerManager.closeScanner();
    }
}
