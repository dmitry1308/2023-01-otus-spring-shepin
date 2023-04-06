package ru.otus.spring.shepin.dao.comment;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.spring.shepin.entity.Comment;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public void removeCommentsByBookId(String id) {
        val query  = Query.query(where("$id").is(new ObjectId(id)));
        val update = new Update().pull("comment", query);
        mongoTemplate.updateMulti(new Query(), update, Comment.class);
    }

    @Override
    public long getCountCommentsByBookId(String studentId) {
                val aggregation = Aggregation.newAggregation(
                        match(where("book._id").is(studentId)),
                        project().andExclude("_id").and("experience").size().as("size"));

                val arraySizeProjection =
                        mongoTemplate.aggregate(aggregation, Student.class, ArraySizeProjection.class).getUniqueMappedResult();
                return arraySizeProjection == null ? 0 : arraySizeProjection.getSize();
            }
        return 0L;
    }
}
