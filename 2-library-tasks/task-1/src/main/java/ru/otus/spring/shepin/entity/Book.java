package ru.otus.spring.shepin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book {
    private final Long   id;
    private final String name;
    private final Genre  genre;
    private final Author author;
}
