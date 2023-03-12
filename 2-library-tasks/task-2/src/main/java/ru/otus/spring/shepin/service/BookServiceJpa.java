package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class BookServiceJpa implements BookService {
    private final BookRepository bookRepository;

    @Override
    @ShellMethod(value = "Get count books", key = {"get-count-books"})
    public int count() {
        return bookRepository.count();
    }

    @Override
    @Transactional
    @ShellMethod(value = "Insert book command. Arguments: book name, first name author, last name author, genre name",
            key = {"c-b"})
    public Book create(@ShellOption(defaultValue = "Any book") String nameBook,
                       @ShellOption(defaultValue = "Any first name") String firstNameAuthor,
                       @ShellOption(defaultValue = "Any last name") String lastNameAuthor,
                       @ShellOption(defaultValue = "Any genre") String genreName) {

        Author author = Author.builder().firstName(firstNameAuthor).lastName(lastNameAuthor).build();
        Genre genre = Genre.builder().name(genreName).build();

        Book book = Book.builder().name(nameBook).author(author).genre(genre).build();
       return bookRepository.createOrUpdate(book);
    }

    @Override
    @Transactional
    @ShellMethod(value = "Update book by name", key = {"u-book-by-name"})
    public void updateByName(Long id, String name) {
        Book book = bookRepository.getById(id);
        Book updateBook = book.toBuilder().name(name).build();
        bookRepository.update(updateBook);
    }

    @Override
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get book by id", key = {"get-book-by-id"})
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get all books", key = {"get-all-books"})
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    @Transactional
    @ShellMethod(value = "Delete book by id", key = {"del-book-id"})
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @ShellMethod(value = "Get comments by book name", key = {"get-comment-by-book-name"})
    public List<Comment> getCommentsByBookName(@ShellOption(defaultValue = "Book-1") String name) {
        return bookRepository.getCommentsByBookName(name);
    }
}
