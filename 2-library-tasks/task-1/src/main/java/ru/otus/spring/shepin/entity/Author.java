package ru.otus.spring.shepin.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {
    private final long   id;
    private       String lastName;
    private       String firstName;
}
