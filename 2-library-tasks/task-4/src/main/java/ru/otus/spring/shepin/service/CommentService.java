package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createByParams(String bookId, String lastName);
    public List<Comment> getAllCommentsByBookId(String bookId);
    void deleteAllCommentsByBookId(String bookId);
}
