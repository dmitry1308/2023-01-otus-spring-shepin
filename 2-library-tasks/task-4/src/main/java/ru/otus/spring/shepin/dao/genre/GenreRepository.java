package ru.otus.spring.shepin.dao.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, Integer> {
    Optional<Genre> getByName(String name);
}
