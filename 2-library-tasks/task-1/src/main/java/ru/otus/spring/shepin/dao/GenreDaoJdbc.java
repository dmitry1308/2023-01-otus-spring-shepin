package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.mapper.GenreMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final RowMapper<Genre>             genreMapper;

    @Override
    public List<Genre> getAll() {
        String sql = """
                select g.id as id, g.name as name, b.id as book_id, b.name as book_name from genre g
                left join book b on b.genre_id=g.id 
                """;
        return namedParameterJdbcOperations.query(sql, genreMapper);
    }

    @Override
    public Genre create(Genre genre) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", genre.getName());

        String query = "insert into genre (name) values (:name)";
        namedParameterJdbcOperations.update(query, parameters, generatedKeyHolder);

        return genre;
    }

    @Override
    public Genre getByName(String name) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        String sql = """
                select g.id as id, g.name as name, b.id as book_id, b.name as book_name from genre g
                left join book b on b.genre_id=g.id 
                where g.name=:name
                """;

        return namedParameterJdbcOperations.queryForObject(sql, parameters, genreMapper);
    }

    @Override
    public Genre getById(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        String sql = """
                select g.id as id, g.name as name, b.id as book_id, b.name as book_name from genre g
                left join book b on b.genre_id=g.id 
                where g.id=:id
                """;
        return namedParameterJdbcOperations.queryForObject(sql, parameters, genreMapper);
    }

}
