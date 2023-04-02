package ru.otus.spring.shepin.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.shepin.AbstractRepositoryTest;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookRepositoryTest extends AbstractRepositoryTest {

    public static final String         BOOK_NAME     = "bookName";
    public static final String         BOOK_NAME_NEW = "bookName1";
    @Autowired
    private             BookRepository bookRepository;

    @Test
    void test_method_findAll() {
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).isNotEmpty().hasSize(6);
    }

    @Test
    void test_method_findById() {
        Book book = bookRepository.save(Book.builder().name(BOOK_NAME).build());
        assertThat(bookRepository.findById(book.getId())).isNotNull().get().hasFieldOrPropertyWithValue("name", BOOK_NAME);
    }

    @Test
    void test_method_findByName() {
        bookRepository.save(Book.builder().name(BOOK_NAME).build());
        assertThat(bookRepository.findByName(BOOK_NAME)).isNotNull().get().hasFieldOrPropertyWithValue("name", BOOK_NAME);
    }

    @Test
    void test_method_count() {
        long count = bookRepository.count();
        assertThat(count).isEqualTo(6);
    }

    @Test
    void test_method_updateByBookName() {
        Book book = bookRepository.save(Book.builder().name(BOOK_NAME).build());
        bookRepository.updateByBookName(book.getId(), BOOK_NAME_NEW);

        assertThat(bookRepository.findById(book.getId())).isNotNull().get().hasFieldOrPropertyWithValue("name", BOOK_NAME_NEW);
    }

    @Test
    void test_method_deleteById() {

    }
}