package ru.otus.spring.shepin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public int count() {
        String sql = "select count(b.id) from Book b";
        return Math.toIntExact(manager.createQuery(sql, Long.class).getSingleResult());
    }

    @Override
    public Book createOrUpdate(Book book) {
        if (book.getId() == null) {
            manager.persist(book);
            return book;
        }
        return manager.merge(book);
    }

    @Override
    public void update(Book book) {
        manager.merge(book);
    }

    @Override
    public Book getById(long id) {
        String sql = "select b from Book b where b.id = :id";

        final TypedQuery<Book> query = manager.createQuery(sql, Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b from Book b";

        final TypedQuery<Book> query = manager.createQuery(sql, Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        String sql = "delete from Book b where b.id = :id";

        final TypedQuery<Book> query = manager.createQuery(sql, Book.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}