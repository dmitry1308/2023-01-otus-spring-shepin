package ru.otus.shepin.spring.service.person;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.config.AppTestProps;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.logging.LogMe;
import ru.otus.shepin.spring.service.io.InputService;
import ru.otus.shepin.spring.service.io.OutputService;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final InputService  inputService;
    private final OutputService outputService;
    private final AppTestProps appTestProps;
    private final MessageSource messageSource;


    @Override
    @LogMe
    public Person getPersonData() {
        outputService.print(
                messageSource.getMessage("question.name.user",null, appTestProps.getLocale()));
        String personName = inputService.readLine();

        outputService.print(
                messageSource.getMessage("question.old.user",new String[]{personName}, appTestProps.getLocale()));
        String age = inputService.readLine();

        return Person.builder().name(personName).age(Integer.parseInt(age)).build();
    }

}
