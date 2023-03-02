package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Book;

import java.util.List;

public interface BookDao {
    int count();

    void create(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
