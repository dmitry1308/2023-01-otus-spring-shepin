package ru.otus.spring.shepin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.shepin.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre getByName(String name);
}
