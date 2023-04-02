package ru.otus.spring.shepin.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.otus.spring.shepin.AbstractRepositoryTest;
import ru.otus.spring.shepin.dao.author.AuthorRepository;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.dao.comment.CommentRepository;
import ru.otus.spring.shepin.dao.genre.GenreRepository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;
import ru.otus.spring.shepin.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookRepositoryTest extends AbstractRepositoryTest {

    public static final String            BOOK_NAME     = "nameBook 500";
    public static final String            BOOK_NAME_NEW = "new book";
    @Autowired
    private             BookRepository    bookRepository;
    @Autowired
    private             CommentRepository commentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

   private ArrayList<Book> books;


    @BeforeEach
    void setUp(){
        bookRepository.deleteAll();

        books = new ArrayList<>();

        for (int i = 100; i < 103; i++) {
            Author author     = Author.builder().firstName("first name " + i).lastName("last name " + i).build();
            Author saveAuthor = authorRepository.save(author);

            Genre genre     = Genre.builder().name("Genre " + i).build();
            Genre saveGenre = genreRepository.save(genre);

            Book book = Book.builder().name("nameBook " + i).author(saveAuthor).genre(saveGenre).build();
            books.add(book);
        }
        bookRepository.saveAll(books);

        List<Book> bookList = bookRepository.findAll();

        for (int i = 0; i < bookList.size(); i++) {
            Book    book     = bookList.get(i);
            Comment comment1 = Comment.builder().commentText("comment " + i).bookId(book.getId()).build();
            Comment comment2 = Comment.builder().commentText("comment " + i).bookId(book.getId()).build();
            commentRepository.saveAll(List.of(comment1, comment2));
        }
    }

    @Test
    void test_method_findAll() {
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList).isNotEmpty().hasSize(3);
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
        assertThat(count).isEqualTo(3);
    }

    @Test
    void test_method_updateByBookName() {
        Book book = bookRepository.save(Book.builder().name(BOOK_NAME).build());
        bookRepository.updateByBookName(book.getId(), BOOK_NAME_NEW);

        assertThat(bookRepository.findById(book.getId())).isNotNull().get().hasFieldOrPropertyWithValue("name", BOOK_NAME_NEW);
    }

    @Test
    void when_call_method_deleteById_should_delete_comments_and_book() {
        Book book = bookRepository.findByName(books.get(0).getName()).get();

        List<Comment> existComments = commentRepository.findByBookId(book.getId());
        assertThat(existComments).isNotEmpty().hasSize(2);

        bookRepository.deleteById(book.getId());

        List<Comment> deletedComments = commentRepository.findByBookId(book.getId());
        assertThat(deletedComments).isEmpty();

        assertThat(bookRepository.findByName(books.get(0).getName())).isNotPresent();
    }
}