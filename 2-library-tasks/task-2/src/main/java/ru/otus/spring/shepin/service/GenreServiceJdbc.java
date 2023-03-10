package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.GenreDao;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class GenreServiceJdbc implements GenreService {
    private final GenreDao genreDao;

    @Override
    @ShellMethod(value = "create author", key = {"c-g"})
    public Genre create(@ShellOption(defaultValue = "some genre") String name) {
        Genre genre = Genre.builder().name(name).build();
        return genreDao.create(genre);
    }

    @Override
    @ShellMethod(value = "Get genre by name", key = {"get-genre-by-name"})
    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    @ShellMethod(value = "Get all genre", key = {"get-genre"})
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    @ShellMethod(value = "Get books by genre id", key = {"get-b-id"})
    public List<Book> getBooksByGenreId(Long id) {
        return genreDao.getBooksByGenreId(id);
    }
}
