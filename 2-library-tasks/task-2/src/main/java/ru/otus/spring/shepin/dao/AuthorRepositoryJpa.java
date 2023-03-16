package ru.otus.spring.shepin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Author;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<Author> getAll() {
        String sql = "select a from Author a";
        final TypedQuery<Author> query = manager.createQuery(sql, Author.class);
        return query.getResultList();
    }

    @Override
    public Author createOrUpdate(Author author) {
        if (author.getId() == null) {
             manager.persist(author);
            return author;
        }
        return manager.merge(author);
    }

    @Override
    public Author getById(Long id) {
        String sql = "select a from Author a where id = :id";
        final TypedQuery<Author> query = manager.createQuery(sql, Author.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
