package ru.otus.shepin.spring.service.importDataService;

import ru.otus.shepin.spring.entity.TestData;

import java.util.List;


public interface DataImportService {
    List<TestData> importData();
}
