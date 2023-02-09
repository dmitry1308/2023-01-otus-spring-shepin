package ru.otus.shepin.spring.service.personDataService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.personDataService.userCommunication.UserCommunicationService;


@AllArgsConstructor
@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final UserCommunicationService communicationUserService;

    public Person getPersonData() {
        String personName = communicationUserService.askPersonAndGetAnswer("What is your name?");
        String age = communicationUserService.askPersonAndGetAnswer(String.format("How old are you, %s?", personName));

        return Person.builder().name(personName).age(Integer.parseInt(age)).build();
    }
}
