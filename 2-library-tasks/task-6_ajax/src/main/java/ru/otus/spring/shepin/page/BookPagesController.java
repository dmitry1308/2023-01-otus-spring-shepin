package ru.otus.spring.shepin.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookPagesController {

    private final GenreService  genreService;
    private final   AuthorService authorService;

    @GetMapping("/")
    public String listBooksPage() {
        return "list";
    }

    @GetMapping("/editBook")
    public String editBookPage() {
        return "edit";
    }

    @GetMapping("/createBook")
    public String addBookPage(Model model) {
        List<Genre> genres  = genreService.getAll();
        List<Author>    authors = authorService.getAll();

        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);

        return "create";
    }
}
