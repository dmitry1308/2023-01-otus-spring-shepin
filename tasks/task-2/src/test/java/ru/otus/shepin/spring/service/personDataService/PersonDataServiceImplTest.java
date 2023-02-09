package ru.otus.shepin.spring.service.personDataService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.ioService.InputService;

import static org.mockito.Mockito.when;

class PersonDataServiceImplTest {
    private PersonDataServiceImpl personDataService;
    private InputService          inputService;

    @BeforeEach
    void setUp() {
        inputService = Mockito.mock(InputService.class);
        personDataService = new PersonDataServiceImpl(inputService);
    }

    @Test
    void when_call_method_askPersonAndGetAnswer_return_ready_object() {
        when(inputService.readStringWithPrompt("What is your name?")).thenReturn("Dmitry");
        when(inputService.readStringWithPrompt("How old are you, Dmitry?")).thenReturn(String.valueOf(36));

        Person personData = personDataService.getPersonData();
        org.junit.jupiter.api.Assertions.assertEquals("Dmitry", personData.getName());
        Assertions.assertEquals(36, personData.getAge());
    }
}


