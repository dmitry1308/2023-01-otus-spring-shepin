package ru.otus.spring.shepin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.shepin.dao.AuthorRepository;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dao.GenreRepository;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.mapper.BookMapper;

@RestController
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookRepository   bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository  genreRepository;
    private final BookMapper       bookMapper;

    @GetMapping("/api/books")
    public Flux<BookDto> listBooks() {
        return bookRepository.findAll().map(bookMapper::fromObjectToDto);
    }
    @PostMapping("/api/books")
    public Mono<BookDto> addBook(@RequestBody Mono<Book> book) {


      return   bookRepository.save(book).map(bookMapper::fromObjectToDto);
    }
}
