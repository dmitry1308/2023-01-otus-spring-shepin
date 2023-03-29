package ru.otus.spring.shepin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collation = "comment")
public class Comment {

    @Id
    private String id;
    @Indexed(unique = true)
    String commentText;

    @DBRef
    private Book book;

    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }
}
