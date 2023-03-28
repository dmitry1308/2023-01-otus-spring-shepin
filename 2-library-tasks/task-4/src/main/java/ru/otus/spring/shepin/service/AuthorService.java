package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author create(String firstName, String lastName);

    Author getById(Integer id);
}
