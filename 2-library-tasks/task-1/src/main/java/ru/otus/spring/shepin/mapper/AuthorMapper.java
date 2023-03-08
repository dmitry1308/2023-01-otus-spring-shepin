package ru.otus.spring.shepin.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        AuthorExtractor AuthorExtractor = new AuthorExtractor();
        return AuthorExtractor.extractData(resultSet);
    }


    class AuthorExtractor implements ResultSetExtractor {

        @Override
        public Author extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            long id = resultSet.getLong("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");

            ArrayList<Book> list = new ArrayList<>();
            while (resultSet.next()) {
                Long bookId = resultSet.getLong("book_id");
                String bookName = resultSet.getString("book_name");
                Book book = Book.builder().id(bookId).name(bookName).build();
                list.add(book);
            }
            return Author.builder()
                        .id(id)
                        .firstName(firstName)
                        .lastName(lastName)
                        .build();
        }
    }
}
