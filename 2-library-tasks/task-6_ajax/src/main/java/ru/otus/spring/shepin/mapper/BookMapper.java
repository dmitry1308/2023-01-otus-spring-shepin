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

    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;

    public Book fromDomainToObject(BookDtoForSave dto) {

        final Genre genre = genreService.getById(dto.getGenreId());
        final Author author = authorService.getById(dto.getAuthorId());

        return new Book(dto.getName(), genre, author);
    }

    public  BookDto fromObjectToDto(Book book) {
        final AuthorDto authorDto = authorMapper.fromObjectToDto(book.getAuthor());
        final GenreDto  genreDto  = genreMapper.fromObjectToDto(book.getGenre());

        return new BookDto(book.getId(), book.getName(), genreDto, authorDto);
    }
}
