package ru.otus.spring.shepin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.shepin.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
