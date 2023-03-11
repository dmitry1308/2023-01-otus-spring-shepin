package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface BookRepository {
    int count();

    Book createOrUpdate(Book book);

    void update(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);

    List<Comment> getCommentsByBookName(String name);
}
