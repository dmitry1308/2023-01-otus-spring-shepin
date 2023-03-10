package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
    Author createOrUpdate(Author author);
    Author getById(Long id);
}
