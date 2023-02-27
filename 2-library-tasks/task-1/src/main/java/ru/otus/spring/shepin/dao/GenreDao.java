package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreDao {
    int count();

    void insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
