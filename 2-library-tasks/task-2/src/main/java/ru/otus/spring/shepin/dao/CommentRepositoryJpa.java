package ru.otus.spring.shepin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public Comment create(Comment comment) {
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

    @Override
    public List<Comment> getAllCommentsByBook(Long bookId) {
        String sql = """
        select c from Comment c
        join c.book b
        where b.id = :id
        """;
        final TypedQuery<Comment> query = manager.createQuery(sql, Comment.class);
        query.setParameter("id", bookId);
        return query.getResultList();
    }

    @Override
    public void deleteAllCommentsByBookId(Long bookId) {
        String sql = """
        delete from Comment c
        where c.book.id = :id
        """;
        final Query query = manager.createQuery(sql);
        query.setParameter("id", bookId);
        query.executeUpdate();
    }
}
