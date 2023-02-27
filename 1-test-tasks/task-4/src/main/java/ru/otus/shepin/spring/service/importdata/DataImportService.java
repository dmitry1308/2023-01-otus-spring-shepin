package ru.otus.shepin.spring.service.importdata;

import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.exception.TestException;

import java.io.IOException;
import java.util.List;


public interface DataImportService {
    List<TestData> importData() throws IOException, TestException;
}
