package ru.otus.spring.shepin.service.report;

import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public String createBookReport(Book book) {

        String name = book.getName();

        return   """
                Название книги: $name                
                """;
    }

    @Override
    public String createAuthorReport(Author author) {

        return null;
    }

    @Override
    public String createGenreReport(Genre genre) {

        return null;
    }
}
