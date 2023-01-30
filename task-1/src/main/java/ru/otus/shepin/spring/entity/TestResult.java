package ru.otus.shepin.spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestResult {
    int            failAnswer;
    int            rightAnswer;
    List<TestData> testDataList;

    @Override
    public String toString() {
        return String.format(
                "\nFail Answer:%s\nRight answer: %s,\n\nHistory questions and answers%s",
                failAnswer, rightAnswer,testDataList.toString());
    }
}
