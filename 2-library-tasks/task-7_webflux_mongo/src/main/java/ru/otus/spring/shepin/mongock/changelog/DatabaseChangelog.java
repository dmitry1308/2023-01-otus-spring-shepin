package ru.otus.spring.shepin.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import ru.otus.spring.shepin.dao.AuthorRepository;
import ru.otus.spring.shepin.dao.BookRepository;
import ru.otus.spring.shepin.dao.GenreRepository;
import ru.otus.spring.shepin.entity.Author;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Genre;

import java.util.ArrayList;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "dshepin", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenre1", author = "dshepin")
    public void insertGenre1(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genre");

        Document doc = new Document().append("name", "Genre-1");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertGenre2", author = "dshepin")
    public void insertGenre2(GenreRepository repository) {
        Genre genre = Genre.builder().name("Genre-2").build();
        repository.save(genre);
    }


    @ChangeSet(order = "004", id = "insertAuthor1", author = "dshepin")
    public void insertAuthor2(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("author");

        Document doc  = new Document().append("firstName", "first name 1").append("lastName", "last name 1");
        myCollection.insertOne(doc);
    }


    @ChangeSet(order = "005", id = "insertAuthor2", author = "dshepin")
    public void insertAuthor2(AuthorRepository repository) {
        Author author = Author.builder().firstName("first name 2").lastName("last name 2").build();
        repository.save(author);
    }

    @ChangeSet(order = "006", id = "insertBook1", author = "dshepin")
    public void insertBook1(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("book");

        Document document = new Document().append("name", "Book1");
        myCollection.insertOne(document);
    }

    @ChangeSet(order = "007", id = "insertBook2", author = "dshepin")
    public void insertBook2(BookRepository repository) {
        Book book = Book.builder().name("Book2").build();
        repository.save(book);
    }


}
