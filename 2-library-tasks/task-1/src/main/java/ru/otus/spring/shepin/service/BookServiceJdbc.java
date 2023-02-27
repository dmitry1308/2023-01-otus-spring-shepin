package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.dao.BookDao;
import ru.otus.spring.shepin.entity.Book;

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
    public void insert(@ShellOption(defaultValue = "Any Book") String name) {

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
