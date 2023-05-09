package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.BookDtoForSave;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;

public interface BookService {
    int count();

    BookDto create(BookDtoForSave book);

    void updateByName(Long id, String name);

    Book getById(long id);

    List<BookDto> getAll();

    void deleteById(long id);
}
