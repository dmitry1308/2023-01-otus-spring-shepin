package ru.otus.spring.shepin.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    @Column(nullable = false,unique = true)
    private String name;
}
