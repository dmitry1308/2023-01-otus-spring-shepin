package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.AuthorDao;
import ru.otus.spring.shepin.dao.BookDao;
import ru.otus.spring.shepin.dao.GenreDao;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;


import java.util.List;

@Service
@ShellComponent
@RequiredArgsConstructor
public class BookServiceJdbc implements BookService {
    private final BookDao   bookDao;
    private final AuthorDao authorDao;
    private final GenreDao  genreDao;

    @Override
    @ShellMethod(value = "Get count books", key = {"get-count-books"})
    public int count() {
        return bookDao.count();
    }

    @Override
    @ShellMethod(value = "Insert book command. Arguments: book name, first name author, last name author, genre name",
            key = {"c-b"})
    public Book create(@ShellOption(defaultValue = "Any book") String nameBook,
                       @ShellOption(defaultValue = "Any first name") String firstNameAuthor,
                       @ShellOption(defaultValue = "Any last name") String lastNameAuthor,
                       @ShellOption(defaultValue = "Any genre") String genreName) {

        Author author = Author.builder().firstName(firstNameAuthor).lastName(lastNameAuthor).build();
        Genre genre = Genre.builder().name(genreName).build();

        final Author createdAuthor = authorDao.create(author);
        final Genre createdGenre = genreDao.createOrUpdate(genre);

        Book book = Book.builder().name(nameBook).author(createdAuthor).genre(createdGenre).build();
       return bookDao.create(book);
    }

    @Override
    @ShellMethod(value = "Update book by name", key = {"u-book-by-name"})
    public void updateByName(Long id, String name) {
        Book book = bookDao.getById(id);
        Book updateBook = book.toBuilder().name(name).build();
        bookDao.update(updateBook);
    }

    @Override
    @ShellMethod(value = "Get book by id", key = {"get-book-by-id"})
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    @ShellMethod(value = "Get all books", key = {"get-all-books"})
    public List<Book> getAll() {
        return bookDao.getAll();
    }

    @Override
    @ShellMethod(value = "Delete book by id", key = {"del-book-id"})
    public void deleteById(long id) {
        bookDao.deleteById(id);
    }
}
