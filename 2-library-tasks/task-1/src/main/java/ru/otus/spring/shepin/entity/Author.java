package ru.otus.spring.shepin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class Author {
    private Long   id;
    private String lastName;
    private String firstName;

    private List<Book> bookList;
}
