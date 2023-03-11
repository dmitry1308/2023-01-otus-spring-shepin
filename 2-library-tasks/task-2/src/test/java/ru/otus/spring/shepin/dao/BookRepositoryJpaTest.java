package ru.otus.spring.shepin.dao;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DataJpaTest
@Import({BookRepositoryJpa.class})
@DisplayName("Dao для работы с книгами")
class BookRepositoryJpaTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long EXPECTED_QUERIES_COUNT  = 3;
    @Autowired
    private BookRepositoryJpa bookRepoJpa;
    @Autowired
    private TestEntityManager em;


    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void should_return_all_books_with_all_info() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val students = bookRepoJpa.getAll();
        assertThat(students).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                            .allMatch(b -> !b.getName().equals(""))
                            .allMatch(b -> b.getGenre() != null)
                            .allMatch(b -> b.getAuthor() != null)
                            .allMatch(b -> b.getComments() != null && b.getComments().size() > 0);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @Test
    @DisplayName("Получить кол-во книг в библиотеке")
    void count() {
        assertThat(bookRepoJpa.count()).isEqualTo(6);
    }

    @Test
    @DisplayName("Создать книгу")
    void create() {

        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        Genre genre = Genre.builder().name("Genre").build();

        Book book = Book.builder().name("NameBook").author(author).genre(genre).build();

        bookRepoJpa.createOrUpdate(book);

        List<Book> bookList = bookRepoJpa.getAll();

        long count = bookList.stream().filter(book1 -> book1.getName().equals("NameBook")).count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Обновить книгу")
    void update() {
        Book book = bookRepoJpa.getById(10);
        Book updatedBook = book.toBuilder().name("UpdateName").build();
        bookRepoJpa.update(updatedBook);

        Book bookUpdated = bookRepoJpa.getById(10);
        assertThat(bookUpdated.getName()).isEqualTo("UpdateName");
    }

    @Test
    @DisplayName("Поиск книги по id и возврат книги")
    void should_return_book_id() {
        Book book = bookRepoJpa.getById(10);
        assertThat(book).isNotNull();
    }

    @Test
    @DisplayName("Достать все книги из БД")
    void getAll() {
        List<Book> bookList = bookRepoJpa.getAll();
        assertThat(bookList).hasSize(6);
    }

    @Test
    @DisplayName("Удалить книгу по id")
    void deleteById() {
        assertThatCode(() -> bookRepoJpa.getById(10)).doesNotThrowAnyException();

        bookRepoJpa.deleteById(10);

        assertThatThrownBy(() -> bookRepoJpa.getById(10)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}