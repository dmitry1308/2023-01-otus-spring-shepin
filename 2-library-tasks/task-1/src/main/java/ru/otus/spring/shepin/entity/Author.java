package ru.otus.spring.shepin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Author {
    private Long   id;
    private String lastName;
    private String firstName;

    private List<Book> bookList;
}
