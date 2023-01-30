package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;

import java.util.List;


@AllArgsConstructor
public class TestService {
    private TestDataService              testDataService;
    private UserCommunicationWithService communicationUserService;


    public TestResult startTest() {
        System.out.println("\n" + "----------Start Test----------");

        List<TestData> testDataList = testDataService.prepareDataTestByFile("Questions.csv");

        for (TestData testData : testDataList) {
            String personAnswer = communicationUserService.askPersonAndGetAnswer(testData.getQuestion());
            testData.toBuilder().answer(personAnswer);
        }

        // TODO: 30.01.2023 тут возможно будет цикл с бегущими вопросами
        return TestResult.builder().build();
    }

}
