package ru.otus.spring.shepin.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DisplayName("Dao для работы с книгами")
class BookRepositoryJpaTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long EXPECTED_QUERIES_COUNT  = 1;
    @Autowired
    private BookRepository    bookRepo;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Получить кол-во книг в библиотеке")
    void count() {
        assertThat(bookRepo.count()).isEqualTo(EXPECTED_NUMBER_OF_BOOKS);
    }

    @Test
    @DisplayName("Создать книгу")
    void create() {

        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        Genre genre = Genre.builder().name("Genre").build();

        Book book = Book.builder().name("NameBook").author(author).genre(genre).build();

        bookRepo.save(book);

        List<Book> bookList = bookRepo.findAll();

        long count = bookList.stream().filter(book1 -> book1.getName().equals("NameBook")).count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Обновить книгу")
    void update() {
        final Book book = bookRepo.findById(100).get();
        Book updatedBook = book.toBuilder().name("UpdateName").build();
        bookRepo.save(updatedBook);

        Book bookUpdated = bookRepo.findById(100).get();
        assertThat(bookUpdated.getName()).isEqualTo("UpdateName");
    }

    @Test
    @DisplayName("Поиск книги по id и возврат книги")
    void should_return_book_id() {
        Book book = bookRepo.findById(100).get();
        assertThat(book).isNotNull();
    }

    @Test
    @DisplayName("Достать все книги из БД")
    void getAll() {
        List<Book> bookList = bookRepo.findAll();
        assertThat(bookList).hasSize(EXPECTED_NUMBER_OF_BOOKS);
    }

    @Test
    @DisplayName("Удалить книгу по id")
    void deleteById() {
        Book book = em.find(Book.class, 100L);
        bookRepo.deleteById(book.getId());
        em.clear();

         Book deletedBook = bookRepo.findById(book.getId()).get();
        assertThat(deletedBook).isNull();
    }
}