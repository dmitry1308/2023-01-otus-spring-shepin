package ru.otus.spring.shepin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import ru.otus.spring.shepin.dao.AuthorRepository;
import ru.otus.spring.shepin.dao.GenreRepository;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.dto.GenreDto;
import ru.otus.spring.shepin.mapper.AuthorMapper;
import ru.otus.spring.shepin.mapper.GenreMapper;

@Controller
@RequiredArgsConstructor
public class BookPagesController {

    private final AuthorRepository authorRepository;
    private final    GenreRepository  genreRepository;

    private final AuthorMapper authorMapper;
    private final GenreMapper genreMapper;

    @GetMapping("/")
    public String listBooksPage() {
        return "books";
    }

    @GetMapping("/createBook")
    public String addBookPage(Model model) {
        final Flux<GenreDto> genreDtoFlux = genreRepository.findAll()
                                                  .map(genreMapper::fromObjectToDto);
        final Flux<AuthorDto> authorDtoFlux = authorRepository.findAll()
                                                    .map(authorMapper::fromObjectToDto);

        model.addAttribute("genres", genreDtoFlux);
        model.addAttribute("authors", authorDtoFlux);

        return "createBook";
    }
}
