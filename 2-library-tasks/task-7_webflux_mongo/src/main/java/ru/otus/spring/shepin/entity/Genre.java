package ru.otus.spring.shepin.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "genre")
public class Genre {
    @Id
    private String    id;
    private String name;
}
