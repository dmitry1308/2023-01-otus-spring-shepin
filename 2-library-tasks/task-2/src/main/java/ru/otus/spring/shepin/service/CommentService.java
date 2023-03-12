package ru.otus.spring.shepin.service;

import ru.otus.spring.shepin.entity.Comment;

public interface CommentService {
    Comment create(Long bookId, String lastName);

    Comment getById(Long id);
}
