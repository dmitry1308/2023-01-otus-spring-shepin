package ru.otus.spring.shepin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.GenreService;

@Service
@RequiredArgsConstructor
public class BookMapper {
    private final GenreService  genreService;
    private final AuthorService authorService;

    public Book fromDomainToObject(BookDto dto) {
        Genre selectedGenre = genreService.getByName(dto.getGenre().getName());

        String[] firstNameAndLastNameAuthor = dto.getAuthor().getFirstNameAndLastName().split(" ");
        Author   selectedAuthor             = authorService.getByParams(firstNameAndLastNameAuthor[0], firstNameAndLastNameAuthor[1]);

        return new Book(dto.getName(), selectedGenre, selectedAuthor);
    }
}
