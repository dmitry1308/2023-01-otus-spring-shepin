package ru.otus.spring.shepin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.shepin.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getByName(String name);
}
