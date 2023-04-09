package ru.otus.spring.shepin.dao.book;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Book;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findByName(String name);
}
