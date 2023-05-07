package ru.otus.spring.shepin.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dto.BookDto;
import ru.otus.spring.shepin.dto.BookDtoForSave;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;
import ru.otus.spring.shepin.mapper.BookMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceJpa implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper     bookMapper;

    @Override
    public int count() {
        return (int) bookRepository.count();
    }

    @Override
    @Transactional
    public BookDto create(BookDtoForSave dto) {
        final Book book = bookRepository.save(bookMapper.fromDomainToObject(dto));
        return bookMapper.fromObjectToDto(book);
    }

    @Override
    @Transactional
    public void updateByName(Long id, String name) {
        Book book       = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error book findById:" + id));
        Book updateBook = book.toBuilder().name(name).build();
        bookRepository.save(updateBook);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error book findById:" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        final List<Book> books = bookRepository.findAll();

        return books.stream()
                    .map(bookMapper::fromObjectToDto)
                    .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
