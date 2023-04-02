package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.dao.comment.CommentRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class CommentServiceMongo implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository    bookrepo;

    @Override
    @ShellMethod(value = "createByParams comment by bookId", key = {"c-c-by-book-id"})
    public Comment createByParams(@ShellOption(defaultValue = "100") String bookId, @ShellOption(defaultValue = "my_comment") String commentText) {

        final Book    book    = bookrepo.findById(bookId).orElseThrow(() -> new Error(String.format("Book by id = %s not exist!", bookId)));
        final Comment comment = Comment.builder().commentText(commentText).bookId(book.getId()).build();
        return commentRepository.save(comment);
    }

    @Override
    @ShellMethod(value = "Get comments by book id", key = {"get-comments-by-book-id"})
    public List<Comment> getAllCommentsByBookId(String bookId) {
        final Book book = bookrepo.findById(bookId).orElseThrow(() -> new Error(String.format("Book by id = %s not exist!", bookId)));
        return commentRepository.findByBookId(book.getId());
    }

    @Override
    @ShellMethod(value = "Delete comments by book id", key = {"delete-comments-by-book-id"})
    public void deleteAllCommentsByBookId(String bookId) {
        final Book book = bookrepo.findById(bookId).orElseThrow(() -> new Error(String.format("Book by id = %s not exist!", bookId)));
        commentRepository.removeCommentsByBookId(book.getId());
    }
}
