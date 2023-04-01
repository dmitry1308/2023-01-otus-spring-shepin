package ru.otus.spring.shepin.dao.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.shepin.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> , CommentRepositoryCustom {
}