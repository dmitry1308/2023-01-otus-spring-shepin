package ru.otus.spring.shepin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.dto.GenreDto;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Genre;

@Service
@RequiredArgsConstructor
public class GenreMapper {
    public GenreDto fromObjectToDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
