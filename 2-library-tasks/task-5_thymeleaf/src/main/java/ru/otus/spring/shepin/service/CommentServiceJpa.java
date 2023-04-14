package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dao.CommentRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

@Service

@RequiredArgsConstructor
public class CommentServiceJpa implements CommentService {
    private final CommentRepository commentRepository;
    private final BookRepository    bookrepo;

    @Override
    @Transactional
    public Comment createByParams(Long bookId, String commentText) {

        final Book    book    = bookrepo.getById(bookId);
        final Comment comment = Comment.builder().commentText(commentText).book(book).build();
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
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
