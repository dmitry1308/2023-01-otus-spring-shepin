package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author create(String firstName, String lastName);
    Author getByFirstAndLastNAme(String firstName, String lastName);
}
