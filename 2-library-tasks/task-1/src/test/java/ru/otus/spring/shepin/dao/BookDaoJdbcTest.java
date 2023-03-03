package ru.otus.spring.shepin.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.shepin.entity.Book;

import static org.junit.jupiter.api.Assertions.*;


@JdbcTest
@ExtendWith(SpringExtension.class)
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;
    @Autowired
    private AuthorDao   authorDao;
    @Autowired
    private GenreDao    genreDao;


    @Test
    void count() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void getById() {
        Book book = bookDaoJdbc.getById(10);
        Assertions.assertThat(book).isNotNull();
    }

    @Test
    void getAll() {
    }

    @Test
    void deleteById() {
    }
}