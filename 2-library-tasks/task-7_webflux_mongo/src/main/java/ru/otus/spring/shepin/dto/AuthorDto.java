package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link ru.otus.spring.shepin.entity.Author} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AuthorDto implements Serializable {

    private String   id;
    private String lastName;
    private String firstName;
}