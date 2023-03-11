package ru.otus.spring.shepin.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    @Column(nullable = false, unique = true)
    private String name;
    @JoinColumn(name = "genre_id")
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Genre  genre;
    @JoinColumn(name = "author_id")
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Author author;

    @ManyToMany(targetEntity = Comment.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_comment", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
