package ru.otus.spring.shepin.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ru.otus.spring.shepin.dao.GenreRepository;
import ru.otus.spring.shepin.entity.Genre;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "dshepin", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenre1", author = "dshepin")
    public void insertGenre1(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genre");

        var doc = new Document().append("name", "Genre-1");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "insertGenre2", author = "dshepin")
    public void insertGenre2(GenreRepository repository) {
        Genre genre = Genre.builder().name("Genre-2").build();
        repository.save(genre);
    }
}
