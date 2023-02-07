package ru.otus.shepin.spring;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.passService.TestService;
import ru.otus.shepin.spring.service.personDataService.PersonDataService;
import ru.otus.shepin.spring.service.printService.PrintManager;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MenuCommandsProcessorImpl implements MenuCommandsProcessor {
    PersonDataService personDataService;
    TestService  testService;
    PrintManager printManager;


    @Override
    public void showMainTitle() {
        printManager.print("\n" + "---------- Test ----------");
    }

    @Override
    public void handlePerson() {
        Person personData = personDataService.getPersonData();
        printManager.print(personData);
    }

    @Override
    public void handleProcessTest() throws IOException {
        TestResult testResult = testService.startTest();
        printManager.print(testResult);
    }
}







