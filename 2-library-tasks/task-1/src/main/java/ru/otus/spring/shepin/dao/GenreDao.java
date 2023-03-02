package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();

    void create(Genre genre);

    Genre getByName(Genre genre);
}
