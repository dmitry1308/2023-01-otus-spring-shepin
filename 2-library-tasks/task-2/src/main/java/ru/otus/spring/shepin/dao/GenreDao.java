package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();

    Genre create(Genre genre);

    Genre getByName(String name);
    Genre getById(Long id);

    List<Book> getBooksByGenreId(Long id);
}
