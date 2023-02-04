package ru.otus.shepin.spring.service.personDataService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.personDataService.userCommunication.UserCommunicationService;
import ru.otus.shepin.spring.service.personDataService.userCommunication.UserConsoleCommunicationService;

import static org.mockito.Mockito.when;

class PersonDataServiceImplTest {

    private PersonDataServiceImpl personDataService;
    private UserCommunicationService userCommunicationService;

    @BeforeEach
    void setUp() {
        userCommunicationService = Mockito.mock(UserConsoleCommunicationService.class);
        personDataService = new PersonDataServiceImpl(userCommunicationService);
    }

    @Test
    void when_call_method_askPersonAndGetAnswer_return_ready_object() {
        when(userCommunicationService.askPersonAndGetAnswer("What is your name?")).thenReturn("Dmitry");
        when(userCommunicationService.askPersonAndGetAnswer("How old are you, Dmitry?")).thenReturn(String.valueOf(36));

        Person personData = personDataService.getPersonData();
        Assertions.assertEquals("Dmitry", personData.getName());
        Assertions.assertEquals(36, personData.getAge());
    }
}


