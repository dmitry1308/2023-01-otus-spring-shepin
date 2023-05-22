package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@Builder
@Getter
public class BookDto implements Serializable {

    private final String      id;
    private final String    name;
    private final GenreDto  genre;
    private final AuthorDto author;

}
