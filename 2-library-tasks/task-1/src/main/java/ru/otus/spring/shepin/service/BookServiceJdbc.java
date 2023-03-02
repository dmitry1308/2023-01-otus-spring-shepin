package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.BookDao;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;


import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class BookServiceJdbc implements BookService {
    private final BookDao bookDao;

    @Override
    public int count() {
        return 0;
    }

    @Override
    @ShellMethod(value = "Insert book command", key = {"i-book"})
    public void insert(@ShellOption(defaultValue = "Any book") String nameBook,
                       @ShellOption(defaultValue = "Any first name") String firstNameAuthor,
                       @ShellOption(defaultValue = "Any last name") String lastNameAuthor,
                       @ShellOption(defaultValue = "Any genre") String genreName) {

        Author author = Author.builder().firstName(firstNameAuthor).lastName(lastNameAuthor).build();
        Genre genre = Genre.builder().name(genreName).build();
        Book book = Book.builder().author(author).genre(genre).build();
        bookDao.insert(book);
    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    @ShellMethod(value = "Get all book", key = {"get-all-books"})
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
