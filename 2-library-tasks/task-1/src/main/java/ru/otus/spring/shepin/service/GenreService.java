package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreService {
    int count();

    void insert(String name);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
