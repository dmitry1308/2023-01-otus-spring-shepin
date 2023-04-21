package ru.otus.spring.shepin.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dto.BookDto2;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceJpa implements BookService {
    private final BookRepository bookRepository;
    private final GenreService  genreService;
    private final AuthorService authorService;

    @Override
    public int count() {
        return (int) bookRepository.count();
    }

    @Override
    @Transactional
    public Book create(String nameBook, String firstNameAuthor, String lastNameAuthor, String genreName) {

        Author author = Author.builder().firstName(firstNameAuthor).lastName(lastNameAuthor).build();
        Genre  genre  = Genre.builder().name(genreName).build();

        Book book = Book.builder().name(nameBook).author(author).genre(genre).build();
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book create(BookDto2 dto) {
        Genre selectedGenre = genreService.getByName(dto.getGenre().getName());

        String[] firstNameAndLastNameAuthor    = dto.getAuthor().getFirstNameAndLastName().split(" ");
        Author selectedAuthor = authorService.getByParams(firstNameAndLastNameAuthor[0], firstNameAndLastNameAuthor[1]);

        Book book = Book.builder().name(dto.getName()).genre(selectedGenre).author(selectedAuthor).build();

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void updateByName(Long id, String name) {
        Book book       = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error book findById:" + id));
        Book updateBook = book.toBuilder().name(name).build();
        bookRepository.save(updateBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error book findById:" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
