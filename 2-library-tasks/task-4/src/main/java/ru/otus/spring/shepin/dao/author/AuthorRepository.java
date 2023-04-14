package ru.otus.spring.shepin.dao.author;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
