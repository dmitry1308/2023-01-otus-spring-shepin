package ru.otus.spring.shepin.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller @RequiredArgsConstructor
public class BookPagesController {

    @GetMapping("/") public String listBooksPage() {
        return "list";
    }

    @GetMapping("/createBook") public String addBookPage() {
        return "create";
    }
}
