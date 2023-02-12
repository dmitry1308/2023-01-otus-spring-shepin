package ru.otus.shepin.spring.service.personDataService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.logging.LogMe;
import ru.otus.shepin.spring.service.ioService.InputService;
import ru.otus.shepin.spring.service.ioService.OutputService;


@AllArgsConstructor
@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final InputService  inputService;
    private final OutputService outputService;

    @Override
    @LogMe
    public Person getPersonData() {
        outputService.print("What is your name?");
        String personName = inputService.readLine();

        outputService.print(String.format("How old are you, %s?", personName));
        String age = inputService.readLine();

        return Person.builder().name(personName).age(Integer.parseInt(age)).build();
    }

}
