package ru.otus.spring.shepin.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.shepin.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Mono<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
