package ru.otus.spring.shepin.dao;

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

        long id = generatedKeyHolder.getKey().longValue();

        return getById(id);
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

    @Override
    public List<Book> getBooksByGenreId(Long id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        final HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        String sql = """
                select b.id as book_id, b.name as book_name from genre g
                left join book b on b.genre_id=g.id 
                where g.id=:id
                """;
        List<Book> books =  namedParameterJdbcOperations.query(sql, (SqlParameterSource) Map.of().put("id",id),
                new BookExtractor());
        return books;
    }

}
