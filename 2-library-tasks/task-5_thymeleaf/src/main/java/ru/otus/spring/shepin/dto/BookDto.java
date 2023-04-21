package ru.otus.spring.shepin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 2, max = 100000, message = "{name-field-should-has-expected-size}")
    private String name;

    private GenreDto genre;

    private AuthorDto author;
}
