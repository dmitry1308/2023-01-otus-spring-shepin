package ru.otus.shepin.spring.entity;

import lombok.Builder;

@Builder(toBuilder = true)
public class TestResult {
    private int rightAnswerCount;
    private int failAnswer;
}
