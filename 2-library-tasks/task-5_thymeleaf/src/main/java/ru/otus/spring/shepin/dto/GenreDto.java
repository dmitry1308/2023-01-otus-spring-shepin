package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Genre} entity
 */
@AllArgsConstructor
@Getter
public class GenreDto implements Serializable {
    private final String name;
}