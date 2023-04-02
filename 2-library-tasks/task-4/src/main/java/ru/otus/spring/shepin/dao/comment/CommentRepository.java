package ru.otus.spring.shepin.dao.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> , CommentRepositoryCustom {

    List<Comment> findByBookId(String bookId);
}