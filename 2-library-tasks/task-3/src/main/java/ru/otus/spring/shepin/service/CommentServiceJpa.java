package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dao.CommentRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class CommentServiceJpa implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository    bookrepo;

    @Override
    @Transactional
    @ShellMethod(value = "createByParams comment by bookId", key = {"c-c-by-book-id"})
    public Comment createByParams(@ShellOption(defaultValue = "100") Long bookId,
                                  @ShellOption(defaultValue = "my_comment") String commentText) {

        final Book book = bookrepo.getById(bookId);
        final Comment comment = Comment.builder().commentText(commentText).book(book).build();
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get comments by book id", key = {"get-comments-by-book-id"})
    public List<Comment> getAllCommentsByBookId(Long bookId) {
        final Book book = bookrepo.getById(bookId);
        return commentRepository.findByBookId(book.getId());
    }

    @Override
    @Transactional
    public void deleteAllCommentsByBookId(Long bookId) {
        final Book book = bookrepo.getById(bookId);
        commentRepository.deleteCommentsByBook_Id(book.getId());
    }
}
