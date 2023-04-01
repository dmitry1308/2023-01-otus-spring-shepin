package ru.otus.spring.shepin.dao.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, Integer> {
    Genre getByName(String name);
}
