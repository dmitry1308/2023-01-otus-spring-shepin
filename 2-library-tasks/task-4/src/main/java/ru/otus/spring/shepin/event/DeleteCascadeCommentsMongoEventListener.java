package ru.otus.spring.shepin.event;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.spring.shepin.dao.comment.CommentRepository;
import ru.otus.spring.shepin.entity.Book;
import ru.otus.spring.shepin.entity.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeleteCascadeCommentsMongoEventListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id     = source.get("_id").toString();
        List<Comment> byBookId = commentRepository.findByBookId(id);

        commentRepository.deleteAllById(
                byBookId.stream().map(
                        comment -> comment.getId()).collect(Collectors.toCollection(ArrayList::new))
        );
    }
}