package ru.otus.spring.shepin.dao.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.shepin.AbstractRepositoryTest;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentRepositoryTest extends AbstractRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository    bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    private ArrayList<Book> books;

    @BeforeEach
    void setUp(){
        bookRepository.deleteAll();

        books = new ArrayList<>();

        for (int i = 100; i < 103; i++) {

            Book book = Book.builder().name("nameBook " + i).build();
            books.add(book);
        }
        bookRepository.saveAll(books);

        List<Book> bookList = bookRepository.findAll();

        for (int i = 0; i < bookList.size(); i++) {
            Book    book     = bookList.get(i);
            Comment comment1 = Comment.builder().commentText("comment " + i).book(book).build();
            Comment comment2 = Comment.builder().commentText("comment " + i).book(book).build();
            commentRepository.saveAll(List.of(comment1, comment2));
        }
    }

    @Test
    void test_get_all_comments_by_book_id() {
        Book          book     = bookRepository.findByName(books.get(0).getName()).get();
        List<Comment> byBookId = commentRepository.findByBookId(book.getId());
        assertThat(byBookId).isNotEmpty().hasSize(2);
    }
}