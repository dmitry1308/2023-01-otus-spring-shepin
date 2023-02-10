package ru.otus.shepin.spring.service.personDataService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.logging.LogMe;
import ru.otus.shepin.spring.service.ioService.InputService;


@AllArgsConstructor
@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final InputService inputService;

    @Override
    @LogMe
    public Person getPersonData() {
        String personName = inputService.readStringWithPrompt("What is your name?");
        String age = inputService.readStringWithPrompt(String.format("How old are you, %s?", personName));

        return Person.builder().name(personName).age(Integer.parseInt(age)).build();
    }

}
