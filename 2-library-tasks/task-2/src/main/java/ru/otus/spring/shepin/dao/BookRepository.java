package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    int count();

    Book create(Book book);

    void update(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    void deleteById(Long id);
}
