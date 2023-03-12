package ru.otus.spring.shepin.dao;

import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface CommentRepository {
    Comment create(Comment comment);
    Comment getById(Long id);

    void delete(Comment comment);
    List<Comment> getAllCommentsByBook(Long bookId);
    void deleteAllCommentsByBookId(Long bookId);
}
