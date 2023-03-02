package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
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
    @ShellMethod(value = "create author", key = {"c-a"})
    public void create(@ShellOption(defaultValue = "firstNameAuthor") String firstName,
                       @ShellOption(defaultValue = "lastNameAuthor")String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        authorDao.create(author);
    }

    @Override
    @ShellMethod(value = "Get author by first and last name", key = {"get-a-first-last-name"})
    public Author getByFirstAndLastNAme(@ShellOption(defaultValue = "firstNameAuthor") String firstName,
                                        @ShellOption(defaultValue = "lastNameAuthor") String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        return authorDao.getByFirstAndLastNAme(author);
    }

    @Override
    @ShellMethod(value = "Get all authors", key = {"get-authors"})
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
