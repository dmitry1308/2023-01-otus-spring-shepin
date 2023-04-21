package ru.otus.spring.shepin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.BookDto1;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.BookService;
import ru.otus.spring.shepin.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService  bookService;
    private final GenreService  genreService;
    private final AuthorService authorService;

    @GetMapping("/list")
    public String listBooks(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        List<Genre> genres = genreService.getAll();
        List<Author> authors    = authorService.getAll();

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "create";
    }

    @PostMapping(path = "/create")
    public String createBook(@ModelAttribute BookDto1 dto1) {
        Genre selectedGenre = genreService.getByName(dto1.getGenre());

        String[] firstNameAndLastNameAuthor    = dto1.getAuthor().split(" ");
        Author selectedAuthor = authorService.getByParams(firstNameAndLastNameAuthor[0], firstNameAndLastNameAuthor[1]);

        Book book = Book.builder().name(dto1.getName()).genre(selectedGenre).author(selectedAuthor).build();

        bookService.create(book);
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult,
                           @RequestParam(value = "action") String action) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        if (action.equals("update")) {
            bookService.updateByName(bookDto.getId(), bookDto.getName());
        } else if (action.equals("delete")) {
            bookService.deleteById(bookDto.getId());
        }

        return "redirect:/list";
    }
}
