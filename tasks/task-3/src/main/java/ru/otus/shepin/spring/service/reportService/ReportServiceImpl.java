package ru.otus.shepin.spring.service.reportService;

import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.Person;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.entity.TestResult;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public String formPersonReport(Person data) {
        return "Person data:\n" + "name = " + data.getName() + ", age = " + data.getAge();
    }

    @Override
    public String formResultReport(TestResult data) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("-------------------------------------------------------------------------------------\n");
        builder.append("Test Result:");
        builder.append("\n\n");
        builder.append(getCommonResult(data.getFailAnswer(), data.getRightAnswer()));
        builder.append("\n\n");
        builder.append("History questions and answers:");
        builder.append(getHistoryQuestionsAndAnswer(data));
        return builder.toString();
    }

    private String getCommonResult(int failAnswer, int rightAnswer) {
        return String.format("Fail Answer: %s.\nRight answer: %s.",
                failAnswer, rightAnswer);
    }

    private String getHistoryQuestionsAndAnswer(TestResult data) {
        StringBuilder builder = new StringBuilder();
        for (TestData testData : data.getTestDataList()) {
            String historyItem = String.format("Question:\n %s\n  answer: %s,\n  rightAnswer: %s",
                    testData.getQuestion(), testData.getPersonAnswer(), testData.getRightAnswer());
            builder.append(historyItem);
            builder.append("\n");
        }
        return builder.toString();
    }
}
