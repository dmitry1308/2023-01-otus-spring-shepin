package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Author} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthorDto implements Serializable {
    private String firstNameAndLastName;
}