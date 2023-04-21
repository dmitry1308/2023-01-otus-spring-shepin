package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Author} entity
 */
@AllArgsConstructor
@Getter
public class AuthorDto implements Serializable {
    private final String firstNameAndLastName;
}