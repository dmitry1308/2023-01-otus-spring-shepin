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
    private final RowMapper<Book>              bookMapper;


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
        return namedParameterJdbcOperations.queryForObject(sql, params, bookMapper);
    }

    @Override
    public List<Book> getAll() {
        String sql = """
                select b.id  as book_id, b.name as book_name,
                g.id as genre_id, g.name as genre_name,
                a.id as author_id, a.first_name as first_name, a.last_name as last_name 
                from book b
                join genre g on g.id= b.genre_id
                join author a on a.id= b.author_id
                """;

        return namedParameterJdbcOperations.query(sql, bookMapper);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from book where id = :id", params);
    }
}