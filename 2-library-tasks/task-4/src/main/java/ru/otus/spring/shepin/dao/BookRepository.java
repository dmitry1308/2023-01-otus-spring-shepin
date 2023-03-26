package ru.otus.spring.shepin.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, Long> {

    //    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();

    //    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findById(Long id);
}
