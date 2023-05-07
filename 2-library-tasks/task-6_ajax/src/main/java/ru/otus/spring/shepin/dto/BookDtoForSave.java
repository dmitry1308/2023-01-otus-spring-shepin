package ru.otus.spring.shepin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;


@AllArgsConstructor
@Builder
@Getter
public class BookDtoForSave implements Serializable {

    private final String name;
    private final String genre;
    private final String lastName;
    private final String firstName;
}
