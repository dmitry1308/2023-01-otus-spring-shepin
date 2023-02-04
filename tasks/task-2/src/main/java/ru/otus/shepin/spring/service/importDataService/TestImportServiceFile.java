package ru.otus.shepin.spring.service.importDataService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.shepin.spring.entity.TestData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Service
public class TestImportServiceFile implements DataImportService {

    private final String fileName;

    public TestImportServiceFile(@Value("${fileName}") String fileName) {
        this.fileName = fileName;
    }

    public List<TestData> importData() throws IOException {
        List<String> lines = getLines();
        return convertLinesToData(lines);
    }

    protected List<String> getLines() throws IOException {
        ClassLoader classLoader = TestImportServiceFile.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(this.fileName)) {

            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! " + this.fileName);
            }

            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                ArrayList<String> lines = new ArrayList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                return lines;
            }
        }
    }

    protected List<TestData> convertLinesToData(List<String> lines) {
        List<TestData> testDataList = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            TestData testData = createTestData(line);
            testDataList.add(testData);
        }
        return testDataList;
    }

    protected TestData createTestData(String line) {
        String[] questionAnswer = line.split(",");
        String question = questionAnswer[0].trim();
        String answer = questionAnswer[1].trim();
        return TestData.builder()
                .question(question)
                .rightAnswer(answer)
                .build();
    }
}
