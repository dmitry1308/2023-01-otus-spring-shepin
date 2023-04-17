package ru.otus.spring.shepin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.BookService;
import ru.otus.spring.shepin.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService  bookService;
    private final GenreService  genreService;
    private final AuthorService authorService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        List<Genre> genreList = genreService.getAll();
        List<String> genreNameList  = genreList.stream()
                .map(Genre::getName).collect(Collectors.toList());

        List<Author> authorList    = authorService.getAll();
        List<String> autorNameList = authorList.stream()
                .map(author -> author.getFirstName() + " " + author.getLastName()).collect(Collectors.toList());

        model.addAttribute("genres", genreNameList);
        model.addAttribute("authors", autorNameList);
        return "create";
    }

    @PostMapping("/create")
    public String createPage(@RequestParam String name, String genre, String author) {
        Genre selectedGenre = genreService.getByName(genre);

        String[] firstNameAndLastNameAuthor    = author.split(" ");
        Author selectedAuthor = authorService.getByParams(firstNameAndLastNameAuthor[0], firstNameAndLastNameAuthor[1]);

        Book book = Book.builder().name(name).genre(selectedGenre).author(selectedAuthor).build();

        bookService.create(book);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult,
                           @RequestParam(value = "action") String action) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        if (action.equals("update")) {
            bookService.updateByName(bookDto.getId(), bookDto.getName());
        }

        if (action.equals("delete")) {
            bookService.deleteById(bookDto.getId());
        }

        return "redirect:/";
    }
}
