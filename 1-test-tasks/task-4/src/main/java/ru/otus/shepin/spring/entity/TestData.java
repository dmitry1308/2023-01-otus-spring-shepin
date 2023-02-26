package ru.otus.shepin.spring.entity;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class TestData {
    private final String question;
    private final String personAnswer;
    private final String rightAnswer;
}
