package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.CommentRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

@Service
@ShellComponent
@RequiredArgsConstructor
public class CommentServiceJpa implements CommentService {
    private final CommentRepository commentRepository;
    private final BookService       bookService;

    @Override
    @Transactional
    @ShellMethod(value = "create comment by bookId", key = {"c-c-by-book-id"})
    public Comment create(@ShellOption(defaultValue = "100") Long bookId,
                          @ShellOption(defaultValue = "my comment") String commentText) {

        final Book book = bookService.getById(bookId);
        final Comment comment = Comment.builder().commentText(commentText).build();

        return commentRepository.createByBookId(book.getId(), comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Long id) {
        return commentRepository.getById(id);
    }
}
