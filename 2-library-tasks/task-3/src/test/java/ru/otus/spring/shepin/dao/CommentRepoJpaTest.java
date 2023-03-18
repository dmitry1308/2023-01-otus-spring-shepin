package ru.otus.spring.shepin.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CommentRepoJpaTest {
    public static final String               COMMENT_1 = "comment1";
    @Autowired
    private             CommentRepository commentRepositoryJpa;
    @Autowired
    private             BookRepository       bookRepositoryJpa;
    @Autowired
    private             TestEntityManager    testManager;

    @Test
    void create_comment_by_book_id() {

        final Book book = Book.builder().name("bookName").build();
        bookRepositoryJpa.save(book);

        final Comment comment = Comment.builder().commentText(COMMENT_1).book(book).build();
        commentRepositoryJpa.save(comment);

        final Comment createdComment = testManager.find(Comment.class, comment.getId());

        assertThat(createdComment).isNotNull().hasFieldOrPropertyWithValue("commentText", COMMENT_1);
    }

    @DisplayName("Получить все комментарии по идентификатору книги")
    @Test
    void get_all_comments_by_book() {
        final Book book = Book.builder().name("bookName").build();
        bookRepositoryJpa.save(book);

        int countComment = 3;
        for (int i = 1; i <= countComment; i++) {
            final Comment comment = Comment.builder().commentText("Comment  " + i).book(book).build();
            commentRepositoryJpa.save(comment);
        }

        final List<Comment> allCommentsByBook = commentRepositoryJpa.findByBookId(book.getId());
        assertThat(allCommentsByBook).hasSize(3).anyMatch(c -> c.getCommentText().equals("Comment  1")).anyMatch(c -> c.getCommentText().equals("Comment  2")).anyMatch(c -> c.getCommentText().equals("Comment  3"));

    }

    @DisplayName("Удалить все комментарии по идентификатору книги")
    @Test
    void delete_all_comments_by_book() {
        final List<Book> bookList = bookRepositoryJpa.findAll();

        final List<Comment> commentsByBook = commentRepositoryJpa.findByBookId(bookList.get(0).getId());
        commentRepositoryJpa.deleteCommentsByBook_Id(commentsByBook.get(0).getBook().getId());

        final List<Comment> deletedCommentsByBook = commentRepositoryJpa.findByBookId(bookList.get(0).getId());
        assertThat(deletedCommentsByBook).hasSize(0);
    }
}