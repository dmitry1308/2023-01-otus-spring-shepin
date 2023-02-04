package ru.otus.shepin.spring.service.personDataService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.personDataService.userCommunication.UserConsoleCommunicationService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test get person data")
class PersonDataServiceImplTestVar2 {
    @Mock
    private UserConsoleCommunicationService communicationService;
    private PersonDataService personDataService;

    @BeforeEach
    void setUp() {
        when(communicationService.askPersonAndGetAnswer("What is your name?")).thenReturn("Dmitry");
        when(communicationService.askPersonAndGetAnswer("How old are you, Dmitry?")).thenReturn(String.valueOf(36));
        personDataService = new PersonDataServiceImpl(communicationService);
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