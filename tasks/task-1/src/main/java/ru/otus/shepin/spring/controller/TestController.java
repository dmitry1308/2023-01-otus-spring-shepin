package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.passService.TestService;
import ru.otus.shepin.spring.service.personDataService.PersonDataService;
import ru.otus.shepin.spring.service.printService.PrintService;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

import java.io.IOException;

@AllArgsConstructor
public class TestController implements Controller {
    private ScannerService scannerManager;
    private PersonDataService personService;
    private TestService testService;
    private PrintService<String> printStringService;
    private PrintService<TestResult> printResultService;

    public void handle() {
        try {
            printStringService.print("\n" + "---------- Test ----------");

            Person personData = personService.getPersonData();
            printStringService.print(personData.toString());

            TestResult testResult = testService.startTest();
            printResultService.print(testResult);

            scannerManager.closeScanner();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            printStringService.print(e.getMessage());
        }
    }
}
