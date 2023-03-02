package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.AuthorDao;
import ru.otus.spring.shepin.entity.Author;

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
