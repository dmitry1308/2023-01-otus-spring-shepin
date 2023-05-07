package ru.otus.spring.shepin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.BookDtoForSave;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.mapper.BookMapper;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.BookService;
import ru.otus.spring.shepin.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService   bookService;
    private final GenreService  genreService;
    private final AuthorService authorService;
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @GetMapping("/list")
    public List<BookDto> listBooks() {
        List<Book> books = bookService.getAll();
        return books.stream()
                    .map(BookMapper::fromObjectToDto)
                    .collect(Collectors.toList());
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        List<Genre>  genres  = genreService.getAll();
        List<Author> authors = authorService.getAll();

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "create";
    }

    @PostMapping(path = "/create")
    public String createBook(@ModelAttribute BookDto dto) {
        bookService.create(dto);
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") long id,
                           Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping(path = "/edit", params = "update")
    public String editBook(@RequestParam("id") Long id,
                           @Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        bookService.updateByName(id, bookDto.getName());
        return "redirect:/list";
    }

    @PostMapping(path = "/edit", params = "delete")
    public String deleteBook(@RequestParam("id") Long id,
                             @Valid @ModelAttribute("book") BookDto bookDto,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }
        bookService.deleteById(id);
        return "redirect:/list";
    }

    @PostMapping("/api/books")
    @Transactional
    public BookDto addBook(@RequestBody BookDtoForSave bookDto) {
        final Book book = bookRepository.save(bookMapper.fromDomainToObject(bookDto));

        return BookMapper.fromObjectToDto(book);
    }
}
