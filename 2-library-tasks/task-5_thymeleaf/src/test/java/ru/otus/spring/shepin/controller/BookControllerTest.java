package ru.otus.spring.shepin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.shepin.dto.AuthorDto;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.GenreDto;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.service.AuthorService;
import ru.otus.spring.shepin.service.BookService;
import ru.otus.spring.shepin.service.GenreService;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Test
    void test_display_list_books() throws Exception {

        Genre genre = Genre.builder().name("genre").build();
        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        Book book = Book.builder().name("name").author(author).genre(genre).build();

        given(bookService.getAll()).willReturn(List.of(book));

        this.mvc.perform(get("/list")).andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attribute("books", List.of(book)));
    }

    @Test
    void test_display_page_edit_book() throws Exception {
        Genre genre = Genre.builder().name("genre").build();
        Author author = Author.builder().firstName("firstName").lastName("lastName").build();
        Book book = Book.builder().name("name").author(author).genre(genre).build();

        given(bookService.getById(1L)).willReturn(book);

        this.mvc.perform(get("/edit").param("id", String.valueOf(1)))
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attribute("book", book));
    }

    @Test
    void test_display_page_create_book() throws Exception {
        Genre genre = Genre.builder().name("genre").build();
        Author author = Author.builder().firstName("firstName").lastName("lastName").build();

        given(authorService.getAll()).willReturn(List.of(author));
        given(genreService.getAll()).willReturn(List.of(genre));

        this.mvc.perform(get("/create")).andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attribute( "authors",List.of(author)))
                .andExpect(model().attribute( "genres",List.of(genre)))
        ;
    }

    @Test
    void test_create_book() throws Exception {
        this.mvc.perform(post("/create")
                .param("name", "BookName")
                .param("genre", "GenreName")
                .param("author", "FirstName LastName"))
                .andExpect(view().name("redirect:/list"));
    }

    @Test
    void test_update_book() throws Exception {
        BookDto bookDto = new BookDto("name", new GenreDto(), new AuthorDto());

        this.mvc.perform(post("/edit")
                        .param("id", String.valueOf(1L))
                        .param("update", "update")
                        .flashAttr("book", bookDto))
                .andExpect(view().name("redirect:/list"));

        verify(bookService, times(1)).updateByName(1L,"name");
    }

    @Test
    void test_delete_book() throws Exception {
        BookDto bookDto = new BookDto( "name", new GenreDto(), new AuthorDto());

        this.mvc.perform(post("/edit")
                        .param("id", String.valueOf(1L))
                        .param("delete","delete")
                        .flashAttr("book", bookDto));

        verify(bookService, times(1)).deleteById(1L);
    }
}