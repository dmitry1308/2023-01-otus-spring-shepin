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
    private final long genreId;
    private final long authorId;

}
