package ru.otus.spring.shepin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

@Data
@AllArgsConstructor
public class BookDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 2, max = 100000, message = "{name-field-should-has-expected-size}")
    private String name;

    private Genre genre;

    private Author author;

    public Book toDomainObject() {
        return Book.builder().id(id).name(name).build();
    }

    public static BookDto fromDomainObject(BookDto book) {
        return new BookDto(book.getId(), book.getName(), book.getGenre(), book.getAuthor());
    }
}
