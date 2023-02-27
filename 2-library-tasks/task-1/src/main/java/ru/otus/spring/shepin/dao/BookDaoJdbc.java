package ru.otus.spring.shepin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Book;

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


//    @Override
//    public int count() {
//        Integer count = jdbc.queryForObject("select count(*) from book", Integer.class);
//        return count == null ? 0 : count;
//    }
//
//    @Override
//    public void insert(Book book) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("name", book.getName());
//
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//
//        namedParameterJdbcOperations.update("insert into book (id, name) values (:id, :name)", Map.of("id", 2, "name", book.getName()));
//    }
//
//    @Override
//    public Book getById(long id) {
//        return jdbc.query("select id, name from book", new BookMapper());
//    }
//
//    @Override
//    public List<Book> getAll() {
//        Map<String, Object> params = Collections.singletonMap("id", id);
//        namedParameterJdbcOperations.update("delete from book where id = :id", params);
//    }
//
//    @Override
//    public void deleteById(long id) {
//        Map<String, Object> params = Collections.singletonMap("id", id);
//        namedParameterJdbcOperations.update("delete from book where id = :id", params);
//    }
//
//    private static class BookMapper implements RowMapper<Book> {
//
//        @Override
//        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//            long id = resultSet.getLong("id");
//            String name = resultSet.getString("name");
//            return Book.builder().id(id).name(name).build();
//        }
//    }
}
