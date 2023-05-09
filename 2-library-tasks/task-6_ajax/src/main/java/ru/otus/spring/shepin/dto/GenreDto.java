package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Genre} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GenreDto implements Serializable {
    private Long id;
    private String name;
}