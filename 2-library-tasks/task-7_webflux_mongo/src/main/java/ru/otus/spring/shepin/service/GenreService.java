package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreService {
    Genre create(String name);

    Genre getByName(String name);

    Genre getById(long id);

    List<Genre> getAll();
}
