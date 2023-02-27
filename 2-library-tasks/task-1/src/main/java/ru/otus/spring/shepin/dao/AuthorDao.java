package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
}
