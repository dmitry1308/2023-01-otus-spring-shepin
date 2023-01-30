package ru.otus.shepin.spring.entity;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public class TestResult {
    private int rightAnswerCount;
    private int failAnswer;

    private List<TestData> testDataList;


    @Override
    public String toString() {
        return String.format(
                "\nRight answer count: %s;\nFail Answer: %s;\nHistory question and Answer: %s",
                 rightAnswerCount, failAnswer, testDataList.toString());
    }
}
