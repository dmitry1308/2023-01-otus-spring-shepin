package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface BookService {
    int count();

    Book create(String nameBook, String firstNameAuthor, String lastNameAuthor, String genre);

    void updateByName(Long id, String name);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    List<Comment> getCommentsByBookName(String name);
}
