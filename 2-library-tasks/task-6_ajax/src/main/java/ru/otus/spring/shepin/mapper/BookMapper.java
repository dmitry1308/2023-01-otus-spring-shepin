package ru.otus.spring.shepin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.BookDtoForSave;
import ru.otus.spring.shepin.dto.GenreDto;
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

    public static Book fromDomainToObject(BookDto dto) {
        final Genre genre = GenreMapper.fromDtoToObject(dto.getGenre());
        final Author author = AuthorMapper.fromDtoToObject(dto.getAuthor());

        return new Book(dto.getName(), genre, author);
    }

    public Book fromDomainToObject(BookDtoForSave dto) {

        final Genre  genre  = getGenre(dto);
        final Author author = getAuthor(dto.getFirstName(), dto.getLastName());

        return new Book(dto.getName(), genre, author);
    }

    private Author getAuthor(String firstName, String lastName) {
        try {
            return authorService.getByParams(firstName, lastName);
        } catch (Exception e) {
           return authorService.create(firstName, lastName);
        }
    }

    private Genre getGenre(BookDtoForSave dto) {
        final Genre genre = genreService.getByName(dto.getGenre());
        if (genre == null) {
            return genreService.create(dto.getGenre());
        }
        return genre;
    }

    public static BookDto fromObjectToDto(Book book) {
        final AuthorDto authorDto = AuthorMapper.fromObjectToDto(book.getAuthor());
        final GenreDto  genreDto  = GenreMapper.fromObjectToDto(book.getGenre());

        return new BookDto(book.getId(), book.getName(), genreDto, authorDto);
    }
}
