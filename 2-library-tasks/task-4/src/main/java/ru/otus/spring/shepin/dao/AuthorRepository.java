package ru.otus.spring.shepin.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
