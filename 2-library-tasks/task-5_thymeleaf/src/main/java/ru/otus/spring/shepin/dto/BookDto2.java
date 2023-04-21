package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Book} entity
 */
@AllArgsConstructor
@Builder
@Getter
public class BookDto2 implements Serializable {
    private final String    name;
    private final GenreDto  genre;
    private final AuthorDto author;
}