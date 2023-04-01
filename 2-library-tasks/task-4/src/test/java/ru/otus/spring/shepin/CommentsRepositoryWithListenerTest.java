package ru.otus.spring.shepin;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import ru.otus.spring.shepin.dao.book.BookRepository;
import ru.otus.spring.shepin.dao.comment.CommentRepository;
import ru.otus.spring.shepin.event.DeleteCascadeCommentsMongoEventListener;

@DisplayName("KnowledgeRepository при наличии listener-ов в контексте ")
@Import(DeleteCascadeCommentsMongoEventListener.class)
class CommentsRepositoryWithListenerTest extends AbstractRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    @DisplayName("при удалении Book должен удалить удалить комментарии к книге")
    @Test
    void shouldRemoveCommentsWhenRemovingBook() {

        // Загрузка студента и его первого знания
        val books = bookRepository.findAll();
        val book = books.get(0);

        val comments = commentRepository.findByBookId(book.getId());

        // Удаление знания из коллекции знаний
        commentRepository.removeCommentsByBookId(book.getId());



    }

//    @DisplayName("при удалении Knowledge должен удалить его из опыта студента")
//    @Test
//    void shouldRemoveKnowledgeFromStudentExperienceWhenRemovingKnowledge() {
//
//        // Загрузка студента и его первого знания
//        val students = studentRepository.findAll();
//        val student = students.get(0);
//        val experience = student.getExperience();
//        val firstKnowledge = experience.get(0);
//
//        // Удаление знания из коллекции знаний
//        knowledgeRepository.delete(firstKnowledge);
//
//        // Загружаем студента заново и проверяем, что знание действительно удалено (размер стал меньше на 1)
//        val expectedExperienceArrayLength = experience.size() - 1;
//        assertThat(studentRepository.findById(student.getId())).isNotEmpty()
//                .get().extracting(Student::getExperience).asList()
//                .hasSize(expectedExperienceArrayLength);
//
//        // Загружаем размер массива с помощью агрегаций и проверяем, что размер массива в БД тоже изменился
//        val actualExperienceArrayLength = studentRepository.getExperienceArrayLengthByStudentId(student.getId());
//        assertThat(actualExperienceArrayLength).isEqualTo(expectedExperienceArrayLength);
//
//    }
}
