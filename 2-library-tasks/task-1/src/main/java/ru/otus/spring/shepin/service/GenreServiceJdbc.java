package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.GenreDao;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class GenreServiceJdbc implements GenreService {
    private final GenreDao genreDao;

    @Override
    public int count() {
        return 0;
    }

    @Override
    @ShellMethod(value = "Insert genre command", key = {"igenre"})
    public void insert(@ShellOption(defaultValue = "Any Genre") String name) {
        Genre genre = Genre.builder().name(name).build();
        genreDao.insert(genre);
    }

    @Override
    public Genre getById(long id) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
