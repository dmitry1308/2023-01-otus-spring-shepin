package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final JdbcOperations               jdbc;
    private final JdbcTemplate                 jdbcTemplate;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;


    @Override
    public int count() {
        Integer count = jdbc.queryForObject("select count(*) from book", Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public void insert(Book book) {

        {
            String sql = "insert into genre (name) values (?)";



            var decParams = List.of(new SqlParameter(Types.VARCHAR, "name"));

            var pscf = new PreparedStatementCreatorFactory(sql, decParams) {
                {
                    setReturnGeneratedKeys(true);
                    setGeneratedKeysColumnNames("id");
                }
            };

            var psc = pscf.newPreparedStatementCreator(List.of(book.getGenre().getName()));

            var keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(psc, keyHolder);

            var uid = Objects.requireNonNull(keyHolder.getKey()).longValue();

        }




    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject("select id, name from book where id=:id", params, new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, name, author_id, genre_id from book", new BookMapper());
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