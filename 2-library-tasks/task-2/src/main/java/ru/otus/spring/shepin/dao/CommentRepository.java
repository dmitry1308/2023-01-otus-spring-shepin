package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Comment;

public interface CommentRepository {
    Comment createByBookId(Long bookId, Comment comment);
    Comment getById(Long id);

    void delete(Comment comment);
}
