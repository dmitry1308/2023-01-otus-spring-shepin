package ru.otus.spring.shepin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.GenreService;

@Service
@RequiredArgsConstructor
public class AuthorMapper {

    public static Author fromDtoToObject(AuthorDto dto) {
        return new Author(dto.getLastName(),dto.getFirstName());
    }

    public static AuthorDto fromObjectToDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }
}
