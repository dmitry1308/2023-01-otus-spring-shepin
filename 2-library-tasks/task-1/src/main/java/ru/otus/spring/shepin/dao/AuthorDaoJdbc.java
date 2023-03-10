package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final RowMapper<Author> authorMapper;

    @Override
    public List<Author> getAll() {
        String sql = """
                select a.id, a.first_name, a.last_name, b.id as book_id, b.name as book_name  from author a 
                left join book b on b.author_id=a.id
                """;
        return namedParameterJdbcOperations.query(sql, authorMapper);
    }

    @Override
    public Author create(Author author) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("first_name", author.getFirstName());
        parameters.addValue("last_name", author.getLastName());

        String query = "insert into author ( first_name, last_name) values ( :first_name, :last_name)";
        namedParameterJdbcOperations.update(query, parameters, generatedKeyHolder);
        long id = generatedKeyHolder.getKey().longValue();
        return getById(id);
    }

    @Override
    public Author getByFirstAndLastNAme(Author author) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("first_name", author.getFirstName());
        parameters.addValue("last_name", author.getLastName());
        String sql = """
                select a.id, a.first_name, a.last_name, b.id as book_id, b.name as book_name  from author a 
                left join book b on b.author_id=a.id
                where first_name=:first_name and last_name=:last_name
                """;

        return namedParameterJdbcOperations.queryForObject(sql, parameters, authorMapper);
    }

    @Override
    public Author getById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        String sql = """
                select a.id, a.first_name, a.last_name, b.id as book_id, b.name as book_name  from author a 
                left join book b on b.author_id=a.id
                where a.id=:id
                """;
        return namedParameterJdbcOperations.queryForObject(sql, parameters, authorMapper);
    }

    public List<Book> getBooks() {
        // TODO: 07.03.2023 реализовать
        return null;
    }
}
