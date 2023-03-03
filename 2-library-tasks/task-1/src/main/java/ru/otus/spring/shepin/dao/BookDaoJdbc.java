package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
        Genre genre = genreDao.getByName(book.getGenre().getName());

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", book.getName());
        parameters.addValue("genre_id", genre.getId());
        parameters.addValue("author_id", author.getId());

        String query = "insert into book (name, author_id, genre_id) values (:name, :author_id, :genre_id)";
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(query, parameters, generatedKeyHolder);
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", book.getId());
        parameters.addValue("name", book.getName());
        String query = "UPDATE book SET name = :name where id=:id";
        namedParameterJdbcOperations.update(query, parameters);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String sql = """
                select b.id  as book_id, b.name as book_name,
                g.id as genre_id, g.name as genre_name,
                a.id as author_id, a.first_name as first_name, a.last_name as last_name 
                from book b
                join genre g on g.id= b.genre_id
                join author a on a.id= b.author_id
                where b.id=:id 
                """;
        return namedParameterJdbcOperations.queryForObject(sql, params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select id, name,  author_id, genre_id from book", new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from book where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            BookExtractor extractor = new BookExtractor();
            return extractor.extractData(resultSet);
        }
    }


    static class BookExtractor implements ResultSetExtractor {
        public Book extractData(ResultSet resultSet) throws SQLException, DataAccessException {

            long bookId = resultSet.getLong("book_id");
            String bookName = resultSet.getString("book_name");

            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");

            long authorId = resultSet.getLong("author_id");
            String authorFirstName = resultSet.getString("first_name");
            String authorLastName = resultSet.getString("last_name");

            Genre genre = Genre.builder().id(genreId).name(genreName).build();
            Author author = Author.builder().id(authorId).firstName(authorFirstName).lastName(authorLastName).build();

            return Book.builder().id(bookId).name(bookName).genre(genre).author(author).build();
        }
    }
}