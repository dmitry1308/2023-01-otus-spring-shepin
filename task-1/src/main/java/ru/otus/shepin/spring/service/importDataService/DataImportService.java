package ru.otus.shepin.spring.service.importDataService;

import ru.otus.shepin.spring.entity.TestData;

import java.io.IOException;
import java.util.List;


public interface DataImportService {
    List<TestData> importData() throws IOException;
}
