package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.exception.EntityNotFoundException;

import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class BookServiceMongo implements BookService {

    private final BookRepository bookRepository;

    @Override
    @ShellMethod(value = "Get count books", key = {"get-count-books"})
    public int count() {
        return (int) bookRepository.count();
    }

    @Override
    @ShellMethod(value = "Insert book command. Arguments: book name, first name author, last name author, genre name", key = {"c-b"})
    public Book create(@ShellOption(defaultValue = "Any book") String nameBook, @ShellOption(defaultValue = "Any first name") String firstNameAuthor, @ShellOption(defaultValue = "Any last name") String lastNameAuthor, @ShellOption(defaultValue = "Any genre") String genreName) {

        Author author = Author.builder().firstName(firstNameAuthor).lastName(lastNameAuthor).build();
        Genre  genre  = Genre.builder().name(genreName).build();

        Book book = Book.builder().name(nameBook).author(author).genre(genre).build();
        return bookRepository.save(book);
    }

    @Override
    @ShellMethod(value = "Update book by name", key = {"u-book-by-name"})
    public void updateByName(String id, String name) {
        Book book       = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error book findById:" + id));
        Book updateBook = book.toBuilder().name(name).build();
        bookRepository.save(updateBook);
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"get-book-by-id"})
    public Book getById(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error book findById:" + id));
    }

    @Override

    @ShellMethod(value = "Get all books", key = {"get-all-books"})
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @ShellMethod(value = "Delete book by id", key = {"del-book-id"})
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
