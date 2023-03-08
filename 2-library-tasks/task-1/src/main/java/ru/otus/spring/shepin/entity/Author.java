package ru.otus.spring.shepin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Author {
    private Long   id;
    private String lastName;
    private String firstName;
}
