package ru.otus.spring.shepin.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.AuthorRepository;
import ru.otus.spring.shepin.entity.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceJpa implements AuthorService {
    private final AuthorRepository authorDao;

    @Override
    @Transactional
    public Author create(String firstName, String lastName) {
        Author author = Author.builder().firstName(firstName).lastName(lastName).build();
        return authorDao.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getById(Long id) {
        return authorDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return authorDao.findAll();
    }

    @Override
    public Author getByParams(String firstName, String lastName) {
        return authorDao.findByFirstNameAndLastName(firstName, lastName).orElseThrow(RuntimeException::new);
    }
}
