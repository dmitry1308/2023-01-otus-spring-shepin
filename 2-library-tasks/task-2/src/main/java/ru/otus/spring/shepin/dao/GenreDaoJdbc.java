package ru.otus.spring.shepin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.mapper.BookExtractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<Genre> getAll() {
        String sql = "select g from Genre g";

        final TypedQuery<Genre> query = manager.createQuery(sql, Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre createOrUpdate(Genre genre) {
        if (genre.getId() == null) {
            manager.persist(genre);
            return genre;
        }
        return manager.merge(genre);

    }

    @Override
    public Genre getByName(String name) {
        String sql = "select g from Genre g where g.name = :name";

        final TypedQuery<Genre> query = manager.createQuery(sql, Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Genre getById(Long id) {
        String sql = "select g from Genre g where g.id = :id";

        final TypedQuery<Genre> query = manager.createQuery(sql, Genre.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
