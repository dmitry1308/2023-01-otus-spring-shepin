package ru.otus.shepin.spring.service.person_data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import ru.otus.shepin.spring.config.AppTestProps;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.io.InputService;
import ru.otus.shepin.spring.service.io.OutputService;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PersonDataServiceImplTest {
    private PersonDataServiceImpl personDataService;
    private InputService          inputService;
    private OutputService         outputService;
    private MessageSource         messageSource;
    private AppTestProps          appTestProps;

    @BeforeEach
    void setUp() {
        inputService = Mockito.mock(InputService.class);
        outputService = Mockito.mock(OutputService.class);
        messageSource = Mockito.mock(MessageSource.class);

        appTestProps = new AppTestProps("Questions.csv", Locale.getDefault());
        personDataService = new PersonDataServiceImpl(inputService, outputService, appTestProps, messageSource);
    }

    @Test
    void when_call_method_askPersonAndGetAnswer_return_ready_object() {
        when(inputService.readLine()).thenReturn("Dmitry", String.valueOf(36));

        when(messageSource.getMessage("question.name.user", null, appTestProps.getLocale())).thenReturn("text");

        Person personData = personDataService.getPersonData();
        assertEquals("Dmitry", personData.getName());
        assertEquals(36, personData.getAge());
    }
}


