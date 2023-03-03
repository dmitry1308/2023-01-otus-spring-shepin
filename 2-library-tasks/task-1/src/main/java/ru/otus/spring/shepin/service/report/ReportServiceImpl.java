package ru.otus.spring.shepin.service.report;

import org.springframework.stereotype.Service;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

@Service
public class ReportServiceImpl implements ReportService {
    @Override
    public String createBookReport(Book book) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        builder.append("Book name" + book.getName());
        builder.append("Author" + book.getAuthor().getFirstName() + " " + book.getAuthor().getFirstName());
        builder.append("Genre" + book.getGenre().getName());

        return builder.toString();
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
