package ru.otus.shepin.spring.service.passService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.importDataService.DataImportService;
import ru.otus.shepin.spring.service.ioService.InputService;
import ru.otus.shepin.spring.service.ioService.OutputService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    private final DataImportService testDataService;
    private final InputService      inputService;
    private final OutputService     outputService;


    @Override
    public TestResult startTest() throws IOException {
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
