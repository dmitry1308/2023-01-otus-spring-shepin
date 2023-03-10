package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> getAll();

    Genre createOrUpdate(Genre genre);

    Genre getByName(String name);

    Genre getById(Long id);
}
