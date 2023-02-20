package ru.otus.shepin.spring.service.import_data;

import ru.otus.shepin.spring.entity.TestData;

import java.io.IOException;
import java.util.List;


public interface DataImportService {
    List<TestData> importData() throws IOException;
}
