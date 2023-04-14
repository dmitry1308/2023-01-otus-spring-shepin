package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Book;

import java.util.List;

public interface BookService {
    int count();

    Book create(String nameBook, String firstNameAuthor, String lastNameAuthor, String genre);

    void updateByName(String id, String name);

    Book getById(String id);

    List<Book> getAll();

    void deleteById(String id);
}
