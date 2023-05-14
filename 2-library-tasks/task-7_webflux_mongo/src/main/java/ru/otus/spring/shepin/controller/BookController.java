package ru.otus.spring.shepin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.mapper.BookMapper;

@RestController
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @GetMapping("/api/books")
    public Flux<BookDto> listBooks() {
        return bookRepository.findAll().map(bookMapper::fromObjectToDto);
    }
//    @PostMapping("/api/books")
//    public Mono<BookDto> addBook(@RequestBody BookDtoForSave bookDto) {
//         return bookService.create(bookDto);
//    }
}
