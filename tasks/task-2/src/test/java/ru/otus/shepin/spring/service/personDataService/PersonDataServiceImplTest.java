package ru.otus.shepin.spring.service.personDataService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.ioService.InputService;
import ru.otus.shepin.spring.service.ioService.OutputService;

import static org.mockito.Mockito.when;

class PersonDataServiceImplTest {
    private PersonDataServiceImpl personDataService;
    private InputService          inputService;
    private OutputService         outputService;

    @BeforeEach
    void setUp() {
        inputService = Mockito.mock(InputService.class);
        outputService = Mockito.mock(OutputService.class);
        personDataService = new PersonDataServiceImpl(inputService, outputService);
    }

    @Test
    void when_call_method_askPersonAndGetAnswer_return_ready_object() {
        when(inputService.readLine()).thenReturn("Dmitry");
        when(inputService.readLine()).thenReturn(String.valueOf(36));

        Person personData = personDataService.getPersonData();
        org.junit.jupiter.api.Assertions.assertEquals("Dmitry", personData.getName());
        Assertions.assertEquals(36, personData.getAge());
    }
}


