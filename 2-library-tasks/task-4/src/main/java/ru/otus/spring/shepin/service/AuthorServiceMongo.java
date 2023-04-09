package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.author.AuthorRepository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.exception.EntityNotFoundException;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class AuthorServiceMongo implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    @ShellMethod(value = "createOrUpdate author", key = {"c-a"})
    public Author create(@ShellOption(defaultValue = "firstNameAuthor") String firstName,
                       @ShellOption(defaultValue = "lastNameAuthor")String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        return authorRepository.save(author);
    }

    @Override
    public Author getById(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Author by id = %s not exist!", id)));
    }

    @Override
    @ShellMethod(value = "Get all authors", key = {"get-authors"})
    public List<Author> getAll() {
        return authorRepository.findAll();
    }
    
    @Override
    public Author findByFirstAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author by params = %s, %s not exist!", firstName, lastName)));
    }
}
