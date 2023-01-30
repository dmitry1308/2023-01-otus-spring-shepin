package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.Person;


@AllArgsConstructor
public class PersonService {
    private UserCommunicationWithService communicationUserService;

    public Person getPersonData() {
        String personName = communicationUserService.askPersonAndGetAnswer("What is your name?");
        String age = communicationUserService.askPersonAndGetAnswer(String.format("How old are you, %s?", personName));

        return Person.builder().name(personName).age(Integer.parseInt(age)).build();
    }
}
