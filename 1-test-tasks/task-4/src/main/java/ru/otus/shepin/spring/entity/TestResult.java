package ru.otus.shepin.spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
public class TestResult {
    private final int            failAnswer;
    private final int            rightAnswer;
    private final List<TestData> testDataList;
}
