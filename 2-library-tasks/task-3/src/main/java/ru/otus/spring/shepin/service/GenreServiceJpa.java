package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.GenreRepository;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class GenreServiceJpa implements GenreService {
    private final GenreRepository genreDao;

    @Override
    @Transactional
    @ShellMethod(value = "createByParams genre", key = {"c-g"})
    public Genre create(@ShellOption(defaultValue = "some genre") String name) {
        Genre genre = Genre.builder().name(name).build();
        return genreDao.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get genre by name", key = {"get-genre-by-name"})
    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get all genre", key = {"get-genre"})
    public List<Genre> getAll() {
        return genreDao.findAll();
    }
}
