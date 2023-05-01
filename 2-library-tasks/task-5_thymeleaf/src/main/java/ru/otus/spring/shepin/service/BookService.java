package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;

public interface BookService {
    int count();

    Book create(BookDto book);

    void updateByName(Long id, String name);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
