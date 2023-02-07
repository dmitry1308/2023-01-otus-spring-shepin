package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.passService.TestService;
import ru.otus.shepin.spring.service.personDataService.PersonDataService;
import ru.otus.shepin.spring.service.printService.PrintService;
import ru.otus.shepin.spring.service.scannerService.ScannerService;

import java.io.IOException;

@Service
@AllArgsConstructor
public class TestController implements Controller {
    private ScannerService           scannerManager;
    private PersonDataService        personDataService;
    private TestService              testService;
    private PrintService<String>     printStringService;
    private PrintService<TestResult> printResultService;
    private PrintService<Person>     printPersonService;


    public void handle() {
        try {
            printStringService.print("\n" + "---------- Test ----------");

            Person personData = personDataService.getPersonData();
            printPersonService.print(personData);

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
