package ru.otus.spring.shepin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.shepin.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
