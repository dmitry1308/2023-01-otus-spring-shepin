package ru.otus.spring.shepin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBookId(Long bookId);

    void deleteCommentsByBook_Id(Long bookId);
}
