package ru.otus.spring.shepin.service;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.dto.BookDto2;
import ru.otus.spring.shepin.dto.GenreDto;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Тестирование сервиса книги")
class BookServiceJpaTest {
    public static final String LAST_NAME_AUTHOR = "Last name author";
    public static final String GENRE = "genre";
    public static final String NAME_BOOK = "Name book";
    public static final String FIRST_NAME_AUTHOR = "First name author";
    @Autowired
    private BookService    bookService;
    @MockBean
    private BookRepository bookRepositoryJdbc;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void count() {
        when(bookRepositoryJdbc.count()).thenReturn(6L);
        Assertions.assertThat(bookService.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Создать книгу")
    void create() {
        BookDto2 book = BookDto2.builder().name(NAME_BOOK).genre(GenreDto.builder().name("genreName").build()).author(AuthorDto.builder().firstNameAndLastName("firstName lastName").build()).build();
        bookService.create(book);
        verify(bookRepositoryJdbc).save(Book.builder().name(NAME_BOOK).build());
    }

    @Test
    @DisplayName("Обновить книгу по имени")
    void updateByName() {
        Book book = Book.builder().name("Name").build();
        when(bookRepositoryJdbc.findById(1L)).thenReturn(Optional.of(book));
        assertThatCode(() -> bookService.updateByName(1L, "new name")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Получить книгу по id")
    void getById() {

        final Book book1 = Book.builder().name("Name").build();
        when(bookRepositoryJdbc.findById(1L)).thenReturn(Optional.of(book1));
        Book book = bookService.getById(1L);
        assertThat(book).hasFieldOrPropertyWithValue(book.getName(), "Name");
    }

    @Test
    @DisplayName("Получить все книги")
    void getAll() {
        when(bookRepositoryJdbc.findAll()).thenReturn(Collections.singletonList(Book.builder().name("Name").build()));
        List<Book> bookList = bookService.getAll();
        assertThat(bookList).hasSize(1);
    }

    @Test
    @DisplayName("Удалить книгу по id")
    void deleteById() {
        bookService.deleteById(1L);
        verify(bookRepositoryJdbc).deleteById(1L);
    }

    @Test
    @DisplayName("Кинуть исключение, если книга не найдена по id")
    void throw_exception_if_book_did_not_find() {

        assertThatCode(() -> bookService.getById(1L)).isInstanceOf(EntityNotFoundException.class);
    }

}