package ru.otus.spring.shepin.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        GenreExtractor genreExtractor = new GenreExtractor();
        return genreExtractor.extractData(resultSet);
    }


    class GenreExtractor implements ResultSetExtractor {

        @Override
        public Genre extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            ArrayList<Book> list = new ArrayList<>();
            while (resultSet.next()) {
                Long bookId = resultSet.getLong("book_id");
                String bookName = resultSet.getString("book_name");
                Book book = Book.builder().id(bookId).name(bookName).build();
                list.add(book);
            }
            return Genre.builder()
                        .id(id)
                        .name(name)
                        .bookList(list)
                        .build();
        }
    }
}
