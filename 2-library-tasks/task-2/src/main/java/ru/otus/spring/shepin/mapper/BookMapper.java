package ru.otus.spring.shepin.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        BookExtractor extractor = new BookExtractor();
        return extractor.extractData(resultSet);
    }

    class BookExtractor implements ResultSetExtractor {
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
