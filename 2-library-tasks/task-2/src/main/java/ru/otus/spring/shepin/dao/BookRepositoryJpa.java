package ru.otus.spring.shepin.dao;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public int count() {
        String sql = "select count(b.id) from Book b";
        return Math.toIntExact(manager.createQuery(sql, Long.class).getSingleResult());
    }

    @Override
    public Book create(Book book) {
        manager.persist(book);
        return book;
    }

    @Override
    public void update(Book book) {
        manager.merge(book);
    }

    @Override
    public Optional<Book> getById(long id) {
        String sql = "select b from Book b where b.id = :id";

        final TypedQuery<Book> query = manager.createQuery(sql, Book.class);
        query.setParameter("id", id);
        final Book book = query.getSingleResult();
        return Optional.of(book);
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = manager.getEntityGraph("book-genre-entity-graph");

        String sql = "select b from Book b";

        final TypedQuery<Book> query = manager.createQuery(sql, Book.class);
        query.setHint("javax.persistence.fetchgraph",entityGraph);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from Book b where b.id = :id";

        final Query query = manager.createQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}