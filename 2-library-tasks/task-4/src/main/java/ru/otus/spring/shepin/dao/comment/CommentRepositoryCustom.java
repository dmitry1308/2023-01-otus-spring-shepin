package ru.otus.spring.shepin.dao.comment;

import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    void removeCommentsByBookId(String id);

   List<Comment> findByBookId(String bookId);
}
