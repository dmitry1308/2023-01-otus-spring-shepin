package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select id, name from genre", new GenreMapper());
    }

    @Override
    public void create(Genre genre) {
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("name", genre.getName());

            String query = "insert into genre (name) values (:name)";
            namedParameterJdbcOperations.update(query, parameters, generatedKeyHolder);
    }

    @Override
    public Genre getByName(String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        String sql = "select id, name  from genre where name=:name";
        return namedParameterJdbcOperations.queryForObject(sql, parameters, new GenreMapper());
    }


    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return Genre.builder().id(id).name(name).build();
        }
    }
}
