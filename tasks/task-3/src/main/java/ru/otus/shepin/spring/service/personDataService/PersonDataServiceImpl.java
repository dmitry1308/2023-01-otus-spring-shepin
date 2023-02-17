package ru.otus.shepin.spring.service.personDataService;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.config.AppTestProps;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.logging.LogMe;
import ru.otus.shepin.spring.service.ioService.InputService;
import ru.otus.shepin.spring.service.ioService.OutputService;


@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final InputService  inputService;
    private final OutputService outputService;
    private final AppTestProps appTestProps;
    private final MessageSource messageSource;


    public PersonDataServiceImpl(InputService inputService, OutputService outputService, AppTestProps appTestProps, MessageSource messageSource) {
        this.inputService = inputService;
        this.outputService = outputService;
        this.appTestProps = appTestProps;
        this.messageSource = messageSource;
    }

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
