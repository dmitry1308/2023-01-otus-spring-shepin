package ru.otus.shepin.spring.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@Getter
@ToString
public class TestData {
    private final String question;
    private final String personAnswer;
    private final String rightAnswer;
}
