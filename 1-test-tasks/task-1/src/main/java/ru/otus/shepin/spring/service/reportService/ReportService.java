package ru.otus.shepin.spring.service.reportService;

public interface ReportService<T> {
    String formReport(T data);
}
