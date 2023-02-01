package ru.otus.shepin.spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class TestResult {
    int            failAnswer;
    int            rightAnswer;
    List<TestData> testDataList;
}
