package ru.otus.shepin.spring.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder(toBuilder = true)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestData {
    String question;
    String personAnswer;
    String rightAnswer;
}
