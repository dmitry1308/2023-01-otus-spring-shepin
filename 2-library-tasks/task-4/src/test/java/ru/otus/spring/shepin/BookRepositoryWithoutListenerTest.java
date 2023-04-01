package ru.otus.spring.shepin;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.MappingException;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("StudentRepository при отсутствии listener-ов в контексте ")
class BookRepositoryWithoutListenerTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository studentRepository;

    @DisplayName("должен кидать MappingException во время сохранения книги с отсутствующими в БД жанрами")
    @Test
    void shouldThrowMappingExceptionWhenSaveBookWithNewGenre() {
        val student = new Book("book", new Genre("genre"), null);
        assertThatThrownBy(() -> studentRepository.save(student)).isInstanceOf(MappingException.class);
    }
}