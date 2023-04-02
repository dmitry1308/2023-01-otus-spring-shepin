package ru.otus.spring.shepin.dao.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.shepin.AbstractRepositoryTest;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommentRepositoryTest extends AbstractRepositoryTest {
    public static final String NAME_BOOK_100 = "nameBook 100";
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository    bookRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void test_get_all_comments_by_book_id() {
        Book          book     = bookRepository.findByName(NAME_BOOK_100).get();
        List<Comment> byBookId = commentRepository.findByBookId(book.getId());
        assertThat(byBookId).isNotEmpty().hasSize(2);
    }
}