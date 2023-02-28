package ru.otus.shepin.spring.service.person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.service.io.InputService;
import ru.otus.shepin.spring.service.io.OutputService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Test enter person data")
class PersonServiceImplTest {
    @Autowired
    private PersonServiceImpl personDataService;
    @MockBean
    private InputService      inputService;
    @MockBean
    private OutputService     outputService;
    @BeforeEach
    void setUp() {
        when(inputService.readLine()).thenReturn("Dmitry", String.valueOf(36));
    }

    @Test
    void when_call_method_askPersonAndGetAnswer_return_ready_object() {
        verify(outputService,times(2));
        Person personData = personDataService.getPersonData();

        assertThat(personData.getName()).isEqualTo("Dmitry");
        assertThat(personData.getAge()).isEqualTo(36);
    }
}


