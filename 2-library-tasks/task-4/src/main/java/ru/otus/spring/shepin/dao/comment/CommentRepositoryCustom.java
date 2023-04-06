package ru.otus.spring.shepin.dao.comment;

public interface CommentRepositoryCustom {
    void removeCommentsByBookId(String id);

     long getCountCommentsByBookId(String studentId);

}
