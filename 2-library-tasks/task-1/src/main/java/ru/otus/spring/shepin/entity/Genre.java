package ru.otus.spring.shepin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class Genre {


    private              Long id;
    private  String name;

}
