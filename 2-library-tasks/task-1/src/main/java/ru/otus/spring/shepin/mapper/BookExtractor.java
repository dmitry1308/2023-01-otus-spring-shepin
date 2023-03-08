package ru.otus.spring.shepin.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookExtractor implements ResultSetExtractor<List<Book>> {
    @Override
    public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        ArrayList<Book> list = new ArrayList<>();
        while (resultSet.next()) {
            Long bookId = resultSet.getLong("book_id");
            String bookName = resultSet.getString("book_name");
            Book book = Book.builder().id(bookId).name(bookName).build();
            list.add(book);
        }
        return list;
    }
}
