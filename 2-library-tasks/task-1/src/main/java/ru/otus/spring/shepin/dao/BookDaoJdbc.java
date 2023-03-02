package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
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
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations               jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao                    authorDao;
    private final GenreDao                     genreDao;


    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from book", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void create(Book book) {
        authorDao.create(book.getAuthor());
        genreDao.create(book.getGenre());

        Author author = authorDao.getByFirstAndLastNAme(book.getAuthor());
        Genre genre = genreDao.getByName(book.getGenre());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", book.getName());
        parameters.addValue("genre_id", genre);
        parameters.addValue("author_id", author.getId());

        String query = "insert into book (name, author_id, genre_id) values (:name, :author_id, :genre_id)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(query, parameters, generatedKeyHolder);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select id, name from book where id=:id", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, name from book", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
//        Map<String, Object> params = Collections.singletonMap("id", id);
//        namedParameterJdbcOperations.update("delete from book where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return Book.builder().id(id).name(name).build();
        }
    }
}