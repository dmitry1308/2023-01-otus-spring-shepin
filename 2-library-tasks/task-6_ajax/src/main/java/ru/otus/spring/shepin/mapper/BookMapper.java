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

@Service
@RequiredArgsConstructor
public class BookMapper {

    private final GenreMapper genreMapper;
    private final AuthorMapper authorMapper;

    public Book fromDomainToObject(BookDtoForSave dto, Genre genre, Author author) {
        return new Book(dto.getName(), genre, author);
    }

    public  BookDto fromObjectToDto(Book book) {
        final AuthorDto authorDto = authorMapper.fromObjectToDto(book.getAuthor());
        final GenreDto  genreDto  = genreMapper.fromObjectToDto(book.getGenre());

        return new BookDto(book.getId(), book.getName(), genreDto, authorDto);
    }
}
