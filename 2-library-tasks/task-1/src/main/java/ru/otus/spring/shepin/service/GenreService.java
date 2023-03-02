package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreService {
    void create(String name);
    Genre getByName(String name);
    List<Genre> getAll();

}
