package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.AuthorRepository;
import ru.otus.spring.shepin.entity.Author;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceJpa implements AuthorService {
    private final AuthorRepository authorDao;

    @Override
    @ShellMethod(value = "createOrUpdate author", key = {"c-a"})
    public Author create(@ShellOption(defaultValue = "firstNameAuthor") String firstName,
                       @ShellOption(defaultValue = "lastNameAuthor")String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        return authorDao.save(author);
    }

    @Override
    public Author getById(Long id) {
        return authorDao.findById(id).orElseThrow(() -> new Error(String.format("Author by id = %s not exist!", id)));
    }

    @Override
    @ShellMethod(value = "Get all authors", key = {"get-authors"})
    public List<Author> getAll() {
        return authorDao.findAll();
    }
}
