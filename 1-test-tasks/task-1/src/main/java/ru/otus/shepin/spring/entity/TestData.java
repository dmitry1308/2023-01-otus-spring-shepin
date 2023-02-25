package ru.otus.shepin.spring.entity;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class TestData {
    String question;
    String personAnswer;
    String rightAnswer;

    @Override
    public String toString() {
        return String.format("\n\nQuestion:\n %s\n  answer: %s,\n  rightAnswer: %s", question, personAnswer,
                rightAnswer);
    }
}
