package ru.otus.spring.shepin.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.entity.Book;

@Component
@RequiredArgsConstructor
public class DeleteCascadeCommentsMongoEventListener extends AbstractMongoEventListener<Book> {
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id     = source.get("_id").toString();
//        mongoOperations.save(((User) source).getEmailAddress());
    }
}