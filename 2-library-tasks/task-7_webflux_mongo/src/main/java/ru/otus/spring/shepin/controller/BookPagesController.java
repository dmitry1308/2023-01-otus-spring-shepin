package ru.otus.spring.shepin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BookPagesController {

    @GetMapping("/")
    public String listBooksPage() {
        return "books";
    }

    @GetMapping("/createBook")
    public String addBookPage(Model model) {
//        final List<Genre> genres = genreService.getAll();
//        final List<Author> authors = authorService.getAll();
//
//        model.addAttribute("genres", genres);
//        model.addAttribute("authors", authors);

        return "createBook";
    }
}
