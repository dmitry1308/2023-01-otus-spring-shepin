package ru.otus.spring.shepin.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.dao.comment.CommentRepository;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteCascadeCommentsMongoEventListener extends AbstractMongoEventListener<Comment> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Comment> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id     = source.get("_id").toString();
        List<Comment> byBookId = commentRepository.findByBookId(id);
    }
}