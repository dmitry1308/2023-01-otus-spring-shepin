package ru.otus.spring.shepin.dao.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.shepin.AbstractRepositoryTest;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Book;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorRepositoryTest extends AbstractRepositoryTest {

    public static final String NAME_BOOK_100 = "nameBook 100";
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository   bookRepository;


    @Test
    void when_call_method_delete_author_then_will_be_deleted_link_book_to_author() {
        Book   book     = bookRepository.findByName(NAME_BOOK_100).get();
        String authorId = book.getAuthor().getId();

        authorRepository.deleteById(authorId);
        assertThat(authorRepository.findById(authorId)).isEmpty();

        Book book1 = bookRepository.findByName(NAME_BOOK_100).get();

        assertThat(book1).isNotNull().hasFieldOrPropertyWithValue("author", null);
    }
}