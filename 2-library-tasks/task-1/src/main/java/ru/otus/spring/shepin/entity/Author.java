package ru.otus.spring.shepin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class Author {
    private final long   id;
    private       String lastName;
    private       String firstName;
}
