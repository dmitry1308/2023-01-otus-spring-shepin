package ru.otus.shepin.spring.utils;

import ru.otus.shepin.spring.entity.TestData;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    public static List<TestData> convertLinesToData(List<String> lines) {
        List<TestData> testDataList = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            createTestData(lines, testDataList, i);
        }
        return testDataList;
    }

    private static void createTestData(List<String> lines, List<TestData> testDataList, int i) {
        String line = lines.get(i);
        String[] questionAnswer = line.split("Answer:");
        String question = questionAnswer[0].trim();
        String answer = questionAnswer[1].trim();
        TestData testData = TestData.builder().question(question).rightAnswer(answer).build();
        testDataList.add(testData);
    }
}
