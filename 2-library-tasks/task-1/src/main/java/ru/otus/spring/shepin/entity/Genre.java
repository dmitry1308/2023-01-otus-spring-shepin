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
public class Genre {
    private Long   id;
    private String name;
    private List<Book> bookList;

}
