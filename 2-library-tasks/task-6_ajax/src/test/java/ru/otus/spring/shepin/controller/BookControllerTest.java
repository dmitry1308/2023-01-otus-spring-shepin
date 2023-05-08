package ru.otus.spring.shepin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.shepin.page.BookPagesController;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.GenreService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookPagesController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private  GenreService  genreService;

    @MockBean
    private   AuthorService authorService;

    @Test
    void test_display_list_books() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(view().name("list"));
    }

    @Test
    void test_display_page_create_book() throws Exception {
        this.mvc.perform(get("/createBook")).andExpect(status().isOk())
                .andExpect(view().name("create"));
    }
}