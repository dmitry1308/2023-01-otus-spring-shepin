package ru.otus.spring.shepin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.BookDtoForSave;
import ru.otus.spring.shepin.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService   bookService;

    @GetMapping("/api/books")
    public List<BookDto> listBooks() {
        return bookService.getAll();
    }
    @PostMapping("/api/books")
    public BookDto addBook(@RequestBody BookDtoForSave bookDto) {
         return bookService.create(bookDto);
    }
}
