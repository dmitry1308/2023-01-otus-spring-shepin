package ru.otus.shepin.spring.entity;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class TestData {
    String question;
    String answer;
    String rightAnswer;
}
