package ru.otus.spring.shepin.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.dao.author.AuthorRepository;
import ru.otus.spring.shepin.dao.genre.GenreRepository;
import ru.otus.spring.shepin.entity.Book;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final GenreRepository  genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        val book = event.getSource();
        if (book.getGenre() != null) {
            genreRepository.save(book.getGenre());
        }

        if (book.getAuthor() != null) {
            authorRepository.save(book.getAuthor());
        }
    }
}
