package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto1 {

    private String name;

    private String genre;

    private String author;
}
