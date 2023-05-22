package ru.otus.spring.shepin.service;

import java.util.List;

public interface CommentService {
    Comment createByParams(Long bookId, String lastName);
    public List<Comment> getAllCommentsByBookId(Long bookId);
    void deleteAllCommentsByBookId(Long bookId);
}
