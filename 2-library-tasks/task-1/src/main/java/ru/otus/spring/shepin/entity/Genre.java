package ru.otus.spring.shepin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class Genre {
    private Long   id;
    private String name;
    private List<Book> bookList;

}
