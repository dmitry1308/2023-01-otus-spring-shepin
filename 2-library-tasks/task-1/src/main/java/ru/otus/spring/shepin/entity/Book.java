package ru.otus.spring.shepin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class Book {
    private final Long   id;
    private final String name;
    private final Genre  genre;
    private final Author author;
}
