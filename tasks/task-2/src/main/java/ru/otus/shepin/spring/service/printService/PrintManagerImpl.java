package ru.otus.shepin.spring.service.printService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;


@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrintManagerImpl implements PrintManager {
    PrintService<Person>     printTestDataService;
    PrintService<TestResult> printTestResultService;
    PrintService<String>     printStringService;

    @Override
    public void print(Object data) {
        if (data instanceof TestData) {
            printTestDataService.print((Person) data);
        }

        if (data instanceof TestResult) {
            printTestResultService.print((TestResult) data);
        }

        if (data instanceof String) {
            printStringService.print((String) data);
        }
    }
}
