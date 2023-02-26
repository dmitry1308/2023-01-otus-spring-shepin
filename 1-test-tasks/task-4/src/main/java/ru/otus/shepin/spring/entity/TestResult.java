package ru.otus.shepin.spring.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class TestResult {
    private final int            failAnswer;
    private final int            rightAnswer;
    private final List<TestData> testDataList;
}
