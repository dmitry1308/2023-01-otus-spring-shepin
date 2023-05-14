package ru.otus.spring.shepin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.shepin.dao.GenreRepository;
import ru.otus.spring.shepin.entity.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceJpa implements GenreService {
    private final GenreRepository genreDao;

    @Override
    @Transactional
    public Genre create(String name) {
        Genre genre = Genre.builder().name(name).build();
        return genreDao.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public Genre getByName(String name) {
        return genreDao.getByName(name);
    }

    @Override public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAll() {
        return genreDao.findAll();
    }
}
