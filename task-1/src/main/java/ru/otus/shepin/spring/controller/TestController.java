package ru.otus.shepin.spring.controller;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.service.ScannerManager;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.PersonService;

@AllArgsConstructor
public class TestController {
    private PersonService personService;
    private ScannerManager scannerManager;


    public void startTest() {
        Person personData = personService.getPersonData();
        System.out.println(personData);

//        InputStream stream = resourcesUtils.getFileFromResourceAsStream("Questions.csv");
//        resourcesUtils.printInputStream(stream);

        scannerManager.closeScanner();
    }
}
