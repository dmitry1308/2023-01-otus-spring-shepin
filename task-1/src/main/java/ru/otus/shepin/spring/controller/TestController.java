package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.PersonService;
import ru.otus.shepin.spring.service.PrintService;
import ru.otus.shepin.spring.service.ScannerService;
import ru.otus.shepin.spring.service.TestService;

@AllArgsConstructor
public class TestController {
    private ScannerService scannerManager;
    private PersonService  personService;
    private TestService  testService;
    private PrintService printService;

    public void passTest() {
        printService.print("\n" + "---------- Test ----------");

        Person personData = personService.getPersonData();
        printService.print(personData.toString());

        TestResult testResult = testService.startTest();
        printService.printResult(testResult);

        scannerManager.closeScanner();
    }
}
