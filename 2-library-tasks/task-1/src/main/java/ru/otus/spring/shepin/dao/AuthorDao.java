package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();

    void create(Author author);


    Author getByFirstAndLastNAme(Author author);
}
