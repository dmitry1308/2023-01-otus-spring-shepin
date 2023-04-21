package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Genre} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenreDto implements Serializable {
    private String name;
}