package ru.otus.spring.shepin.dao;

public interface CommentRepositoryCustom {
    void removeCommentsByBookId(String id);
}
