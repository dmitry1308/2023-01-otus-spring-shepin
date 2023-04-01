package ru.otus.spring.shepin.dao.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.shepin.entity.Book;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateByBookName(String id, String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Book foundBook = mongoTemplate.findOne(query, Book.class);

        foundBook.setName(name);

        mongoTemplate.save(foundBook);
    }
}
