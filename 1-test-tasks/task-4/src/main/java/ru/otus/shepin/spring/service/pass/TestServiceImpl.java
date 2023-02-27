package ru.otus.shepin.spring.service.pass;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.exception.TestException;
import ru.otus.shepin.spring.service.importdata.DataImportService;
import ru.otus.shepin.spring.service.io.InputService;
import ru.otus.shepin.spring.service.io.OutputService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ShellComponent
@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    private final DataImportService testDataService;
    private final InputService      inputService;
    private final OutputService     outputService;


    @Override
    @ShellMethod(value = "Pass test", key = {"test"})
    public TestResult startTest() throws IOException, TestException {
        outputService.print("\n" + "----------Start Test----------");
        List<TestData> testDataList = testDataService.importData();
        return askQuestions(testDataList);
    }

    private TestResult askQuestions(List<TestData> testDataList) {
        int failAnswer = 0;
        int rightAnswer = 0;

        List<TestData> testDataListWithAnswer = new ArrayList<>();

        for (TestData testData : testDataList) {
            outputService.print(testData.getQuestion());
            String personAnswer = inputService.readLine();
            TestData testDataWithAnswerPerson = testData.toBuilder().personAnswer(personAnswer).build();
            testDataListWithAnswer.add(testDataWithAnswerPerson);

            if (testData.getRightAnswer().equals(personAnswer)) {
                rightAnswer++;
            } else {
                failAnswer++;
            }
        }

        return TestResult.builder().rightAnswer(rightAnswer).failAnswer(failAnswer).testDataList(testDataListWithAnswer).build();
    }

}
