package ru.otus.spring.shepin.service.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.shepin.entity.Book;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ReportServiceImplTest {

    @Autowired
    private ReportService reportService;
    @Test
    void createBookReport() {
        Book book = Book.builder().name("Book name").build();
        String bookReport = reportService.createBookReport(book);
        System.out.println(bookReport);
    }
}