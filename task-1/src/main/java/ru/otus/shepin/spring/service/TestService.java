package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
public class TestService {
    private TestDataService              testDataService;
    private UserCommunicationWithService communicationUserService;


    public TestResult startTest() {
        System.out.println("\n" + "----------Start Test----------");

        List<TestData> testDataList = testDataService.prepareDataTestByFile("Questions.csv");

        return askQuestions(testDataList);
    }

    private TestResult askQuestions(List<TestData> testDataList) {
        int failAnswer = 0;
        int rightAnswer = 0;

        List<TestData> testDataListWithAnswer = new ArrayList<>();

        for (TestData testData : testDataList) {
            String personAnswer = communicationUserService.askPersonAndGetAnswer(testData.getQuestion());
            TestData testDataWithAnswerPerson = testData.toBuilder().personAnswer(personAnswer).build();
            testDataListWithAnswer.add(testDataWithAnswerPerson);

            if (testData.getRightAnswer().equals(personAnswer)) {
                rightAnswer++;
            } else {
                failAnswer++;
            }
        }

        return TestResult.builder().rightAnswerCount(rightAnswer).failAnswer(failAnswer).testDataList(testDataListWithAnswer).build();
    }

}
