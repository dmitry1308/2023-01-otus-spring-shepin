package ru.otus.shepin.spring.service.passService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;
import ru.otus.shepin.spring.service.importDataService.DataImportService;
import ru.otus.shepin.spring.service.personDataService.userCommunication.UserCommunicationService;
import ru.otus.shepin.spring.service.printService.PrintService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    private final DataImportService        testDataService;
    private final UserCommunicationService communicationUserService;
    @Qualifier("printStringService")
    private final PrintService<String>     printService;


    public TestResult startTest() throws IOException {
        printService.print("\n" + "----------Start Test----------");
        List<TestData> testDataList = testDataService.importData();
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

        return TestResult.builder().rightAnswer(rightAnswer).failAnswer(failAnswer).testDataList(testDataListWithAnswer).build();
    }

}
