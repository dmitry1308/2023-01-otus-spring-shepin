package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.AuthorDao;
import ru.otus.spring.shepin.dao.GenreDao;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceJdbc implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    @ShellMethod(value = "Get all authors", key = {"get-authors"})
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
