package ru.otus.spring.shepin.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({CommentRepositoryJpa.class, BookRepositoryJpa.class})
class CommentRepoJpaTest {
    public static final String               COMMENT_1 = "comment1";
    @Autowired
    private             CommentRepositoryJpa commentRepositoryJpa;
    @Autowired
    private             BookRepositoryJpa    bookRepositoryJpa;
    @Autowired
    private             TestEntityManager    testManager;

    @Test
    void create_comment_by_book_id() {

        final Book book = Book.builder().name("bookName").build();
        bookRepositoryJpa.create(book);

        final Comment comment = Comment.builder().commentText(COMMENT_1).book(book).build();
        commentRepositoryJpa.create(comment);

        final Comment createdComment = testManager.find(Comment.class, comment.getId());

        assertThat(createdComment).isNotNull().hasFieldOrPropertyWithValue("commentText", COMMENT_1);
    }

    @DisplayName("Получить все комментарии по идентификатору книги")
    @Test
    void get_all_comments_by_book() {
        final Book book = Book.builder().name("bookName").build();
        bookRepositoryJpa.create(book);

        int countComment = 3;
        for (int i = 1; i <= countComment; i++) {
            final Comment comment = Comment.builder().commentText("Comment  " + i).book(book).build();
            commentRepositoryJpa.create(comment);
        }

        final List<Comment> allCommentsByBook = commentRepositoryJpa.getAllComments(book.getId());
        assertThat(allCommentsByBook).hasSize(3)
                                     .anyMatch(c -> c.getCommentText().equals("Comment  1"))
                                     .anyMatch(c -> c.getCommentText().equals("Comment  2"))
                                     .anyMatch(c -> c.getCommentText().equals("Comment  3"));

    }

    @DisplayName("Удалить все комментарии по идентификатору книги")
    @Test
    void delete_all_comments_by_book() {
        final List<Book> bookList = bookRepositoryJpa.getAll();

        final List<Comment> commentsByBook = commentRepositoryJpa.getAllComments(bookList.get(0).getId());
        commentRepositoryJpa.deleteAllCommentsByBookId(commentsByBook.get(0).getBook().getId());

        final List<Comment> deletedCommentsByBook = commentRepositoryJpa.getAllComments(bookList.get(0).getId());
        assertThat(deletedCommentsByBook).hasSize(0);
    }
}