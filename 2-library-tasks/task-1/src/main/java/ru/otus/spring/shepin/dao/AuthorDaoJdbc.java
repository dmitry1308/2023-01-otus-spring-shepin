package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, first_name, last_name from author", new AuthorMapper());
    }

    @Override
    public void create(Author author) {
        {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("first_name", author.getFirstName());
            parameters.addValue("last_name", author.getLastName());

            String query = "insert into author ( first_name, last_name) values ( :first_name, :last_name)";
            namedParameterJdbcOperations.update(query, parameters, generatedKeyHolder);
        }
    }

    @Override
    public Author getByFirstAndLastNAme(Author author) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("first_name", author.getFirstName());
        parameters.addValue("last_name", author.getLastName());
        String sql = "select id, first_name, last_name  from author where first_name=:first_name and last_name=:last_name";
        return namedParameterJdbcOperations.queryForObject(sql, parameters, new AuthorMapper());
    }

    @Override
    public Author getById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        String sql = "select id, first_name, last_name  from author where id=:id";
        return namedParameterJdbcOperations.queryForObject(sql, parameters, new AuthorMapper());
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            return Author.builder().id(id).firstName(firstName).lastName(lastName).build();
        }
    }
}
