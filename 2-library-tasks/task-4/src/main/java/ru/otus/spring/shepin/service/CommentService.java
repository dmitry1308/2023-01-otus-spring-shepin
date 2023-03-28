package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createByParams(Integer bookId, String lastName);
    public List<Comment> getAllCommentsByBookId(Integer bookId);
    void deleteAllCommentsByBookId(Integer bookId);
}
