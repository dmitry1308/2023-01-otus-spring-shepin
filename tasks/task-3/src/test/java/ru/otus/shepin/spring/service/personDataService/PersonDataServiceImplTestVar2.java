package ru.otus.shepin.spring.service.personDataService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.shepin.spring.config.AppTestProps;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.ioService.InputService;
import ru.otus.shepin.spring.service.ioService.OutputService;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Component
@ExtendWith(MockitoExtension.class)
@DisplayName("Test get person data")
class PersonDataServiceImplTestVar2 {
    @Mock
    private InputService      inputService;
    @Mock
    private OutputService     outputService;
    @Mock
    private MessageSource     messageSource;
    private PersonDataService personDataService;
    private AppTestProps      appTestProps;

    @BeforeEach
    void setUp() {

        appTestProps = new AppTestProps("Questions.csv", Locale.getDefault());


        when(inputService.readLine()).thenReturn("Dmitry", String.valueOf(36));
        personDataService = new PersonDataServiceImpl(inputService, outputService, appTestProps, messageSource);
    }

    @Test
    @DisplayName("ask data person and get data")
    void getPersonData() {
        Person personData = personDataService.getPersonData();
        assertThat(personData).isNotNull();
        Assertions.assertEquals("Dmitry", personData.getName());
        Assertions.assertEquals(36, personData.getAge());
    }
}