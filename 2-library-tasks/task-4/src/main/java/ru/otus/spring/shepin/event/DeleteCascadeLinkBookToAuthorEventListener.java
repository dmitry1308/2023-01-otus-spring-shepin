package ru.otus.spring.shepin.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.entity.Author;

@Component
@RequiredArgsConstructor
public class DeleteCascadeLinkBookToAuthorEventListener extends AbstractMongoEventListener<Author> {

    private final BookRepository bookRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id     = source.get("_id").toString();

//        val query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
//        val update = new Update().pull("author", query);
//        mongoTemplate.updateMulti(new Query(), update, Book.class);

    }
}