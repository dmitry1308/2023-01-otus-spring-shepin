package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.passService.TestService;
import ru.otus.shepin.spring.service.personDataService.PersonDataService;
import ru.otus.shepin.spring.service.printService.PrintService;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

@AllArgsConstructor
public class TestController implements Controller {
    private ScannerService    scannerManager;
    private PersonDataService personService;
    private TestService       testService;
    private PrintService      printStringService;
    private PrintService      printResultService;

    public void handle() {
        printStringService.print("\n" + "---------- Test ----------");

        Person personData = personService.getPersonData();
        printStringService.print(personData.toString());

        TestResult testResult = testService.startTest();
        printResultService.print(testResult);

        scannerManager.closeScanner();
    }
}
