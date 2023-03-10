package ru.otus.spring.shepin.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.shepin.dao.BookDao;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Тестирование сервиса книги")
class BookServiceJdbcTest {
    @Autowired
    private BookService bookService;
    @MockBean
    private BookDao     bookDaoJdbc;

    @Test
    void count() {
        when(bookDaoJdbc.count()).thenReturn(6);
        Assertions.assertThat(bookService.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Создать книгу")
    void create() {
        bookService.create("Name book", "First name author", "Last name author", "genre");

        Author author = Author.builder().firstName("First name author").lastName("Last name author").build();
        Genre genre = Genre.builder().name("genre").build();
        Book book = Book.builder().name("Name book").author(author).genre(genre).build();
        verify(bookDaoJdbc).create(book);
    }

    @Test
    @DisplayName("Обновить книгу по имени")
    void updateByName() {
        when(bookDaoJdbc.getById(1L)).thenReturn(Book.builder().id(1L).name("Name").build());
        assertThatCode(() -> bookService.updateByName(1L, "new name")).doesNotThrowAnyException();
        verify(bookDaoJdbc).update(Book.builder().id(1L).name("new name").build());
    }

    @Test
    @DisplayName("Получить книгу по id")
    void getById() {
        when(bookDaoJdbc.getById(1L)).thenReturn(Book.builder().name("Name").build());
        Book book = bookService.getById(1L);
        assertThat(book).hasFieldOrPropertyWithValue(book.getName(), "Name");
    }

    @Test
    @DisplayName("Получить все книги")
    void getAll() {
        when(bookDaoJdbc.getAll()).thenReturn(Collections.singletonList(Book.builder().name("Name").build()));
        List<Book> bookList = bookService.getAll();
        assertThat(bookList).hasSize(1);
    }

    @Test
    @DisplayName("Удалить книгу по id")
    void deleteById() {
        bookService.deleteById(1L);
        verify(bookDaoJdbc).deleteById(1L);
    }
}