package ru.otus.spring.shepin.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, Long> {
    Genre getByName(String name);
}
