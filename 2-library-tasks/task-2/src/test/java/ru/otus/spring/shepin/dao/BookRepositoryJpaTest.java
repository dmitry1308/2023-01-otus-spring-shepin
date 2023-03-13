package ru.otus.spring.shepin.dao;

import lombok.val;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


@DataJpaTest
@Import({BookRepositoryJpa.class})
@DisplayName("Dao для работы с книгами")
class BookRepositoryJpaTest {
    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long EXPECTED_QUERIES_COUNT  = 1;
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
                            .allMatch(b -> b.getAuthor() != null);
        System.out.println("----------------------------------------------------------------------------------------------------------\n\n\n\n");
        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @Test
    @DisplayName("Получить кол-во книг в библиотеке")
    void count() {
        assertThat(bookRepoJpa.count()).isEqualTo(EXPECTED_NUMBER_OF_BOOKS);
    }

    @Test
    @DisplayName("Создать книгу")
    void create() {

        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        Genre genre = Genre.builder().name("Genre").build();

        Book book = Book.builder().name("NameBook").author(author).genre(genre).build();

        bookRepoJpa.create(book);

        List<Book> bookList = bookRepoJpa.getAll();

        long count = bookList.stream().filter(book1 -> book1.getName().equals("NameBook")).count();

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("Обновить книгу")
    void update() {
        Book book = bookRepoJpa.getById(100);
        Book updatedBook = book.toBuilder().name("UpdateName").build();
        bookRepoJpa.update(updatedBook);

        Book bookUpdated = bookRepoJpa.getById(100);
        assertThat(bookUpdated.getName()).isEqualTo("UpdateName");
    }

    @Test
    @DisplayName("Поиск книги по id и возврат книги")
    void should_return_book_id() {
        Book book = bookRepoJpa.getById(100);
        assertThat(book).isNotNull();
    }

    @Test
    @DisplayName("Достать все книги из БД")
    void getAll() {
        List<Book> bookList = bookRepoJpa.getAll();
        assertThat(bookList).hasSize(EXPECTED_NUMBER_OF_BOOKS);
    }

    @Test
    @DisplayName("Удалить книгу по id")
    void deleteById() {
        assertThatCode(() -> bookRepoJpa.getById(100)).doesNotThrowAnyException();
        final Book book = bookRepoJpa.getById(100);
        bookRepoJpa.deleteById(book.getId());
        em.clear();

        final Book deletedBook = em.find(Book.class, book.getId());
        assertThat(deletedBook).isNull();
    }
    @Test
    @DisplayName("Получить все комментарии книги")
    void get_all_comment_by_book_name() {
        List<Book> bookList = bookRepoJpa.getAll();

        final List<Comment> commentsByBookName = bookRepoJpa.getCommentsByBookName(bookList.get(0).getName());

        assertThat(commentsByBookName).hasSize(2);
    }
}