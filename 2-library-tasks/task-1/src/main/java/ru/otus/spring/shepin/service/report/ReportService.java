package ru.otus.spring.shepin.service.report;

import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

public interface ReportService {

    String createBookReport(Book book);
    String createAuthorReport(Author author);
    String createGenreReport(Genre genre);
}
