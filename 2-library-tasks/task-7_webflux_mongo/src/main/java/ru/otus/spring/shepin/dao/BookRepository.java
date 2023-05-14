package ru.otus.spring.shepin.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.shepin.entity.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {


    Flux<Book> findAll();


    Mono<Book> findById(Long id);

    Mono<Book> save(Book book);
}
