package ru.otus.spring.shepin.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAll();
    Optional<Book> findById(String id);
    Optional<Book> findByName(String name);
}
