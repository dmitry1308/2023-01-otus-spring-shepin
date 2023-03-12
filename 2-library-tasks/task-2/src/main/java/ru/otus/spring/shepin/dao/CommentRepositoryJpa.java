package ru.otus.spring.shepin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Comment;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public Comment createByBookId(Long bookId, Comment comment) {

             manager.persist(comment);
            return comment;
    }

    @Override
    public Comment getById(Long id) {
        String sql = "select c from Comment c where id = :id";
        final TypedQuery<Comment> query = manager.createQuery(sql, Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Comment comment) {
        manager.remove(comment);
    }
}
