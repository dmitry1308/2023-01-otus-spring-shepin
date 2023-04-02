package ru.otus.spring.shepin.dao.author;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.shepin.AbstractRepositoryTest;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository   bookRepository;
    private Book saveBook;

    @BeforeEach
    void setUp() {
        Author author     = Author.builder().firstName("first name ").lastName("last name ").build();
        Author saveAuthor = authorRepository.save(author);

        Book book = Book.builder().name("nameBook ").author(saveAuthor).build();
        saveBook = bookRepository.save(book);
    }


    @Test
    void when_call_method_delete_author_then_will_be_deleted_link_book_to_author() {
        Book   book     = bookRepository.findByName(saveBook.getName()).get();
        String authorId = book.getAuthor().getId();

        authorRepository.deleteById(authorId);
        assertThat(authorRepository.findById(authorId)).isEmpty();

        Book book1 = bookRepository.findByName(saveBook.getName()).get();

        assertThat(book1).isNotNull().hasFieldOrPropertyWithValue("author", null);
    }
}