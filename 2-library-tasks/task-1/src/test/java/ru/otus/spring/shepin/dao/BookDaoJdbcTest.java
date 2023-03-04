package ru.otus.spring.shepin.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.mapper.AuthorMapper;
import ru.otus.spring.shepin.mapper.BookMapper;
import ru.otus.spring.shepin.mapper.GenreMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@JdbcTest
@ExtendWith(SpringExtension.class)
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class, AuthorMapper.class, BookMapper.class, GenreMapper.class})
@DisplayName("Dao для работы с книгами")
class BookDaoJdbcTest {
    @Autowired
    private BookDaoJdbc   bookDaoJdbc;


    @Test
    @DisplayName("Получить кол-во книг в библиотеке")
    void count() {
        assertThat(bookDaoJdbc.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Создать книгу")
    void create() {

        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        Genre genre = Genre.builder().name("Genre").build();

        Book book = Book.builder().name("NameBook").author(author).genre(genre).build();

        bookDaoJdbc.create(book);

        List<Book> bookList = bookDaoJdbc.getAll();

        long count = bookList.stream().filter(book1 -> book1.getName().equals("NameBook")).count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Обновить книгу")
    void update() {
        Book book = bookDaoJdbc.getById(10);
        Book updatedBook = book.toBuilder().name("UpdateName").build();
        bookDaoJdbc.update(updatedBook);

        Book bookUpdated = bookDaoJdbc.getById(10);
        assertThat(bookUpdated.getName()).isEqualTo("UpdateName");
    }

    @Test
    @DisplayName("Поиск книги по id и возврат книги")
    void should_return_book_id() {
        Book book = bookDaoJdbc.getById(10);
        assertThat(book).isNotNull();
    }

    @Test
    @DisplayName("Достать все книги из БД")
    void getAll() {
        List<Book> bookList = bookDaoJdbc.getAll();
        assertThat(bookList).hasSize(6);
    }

    @Test
    @DisplayName("Удалить книгу по id")
    void deleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(10)).doesNotThrowAnyException();

        bookDaoJdbc.deleteById(10);

        assertThatThrownBy(() -> bookDaoJdbc.getById(10)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}