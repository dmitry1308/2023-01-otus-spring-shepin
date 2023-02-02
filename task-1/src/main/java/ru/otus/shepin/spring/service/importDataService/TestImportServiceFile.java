package ru.otus.shepin.spring.service.importDataService;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.TestData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TestImportServiceFile implements DataImportService<String> {

    public List<TestData> importData(String fileName) {
        InputStream fileAsStream = getFileFromResourceAsStream(fileName);
        List<String> lines = getLines(fileAsStream);
        return convertLinesToData(lines);
    }

    private List<String> getLines(InputStream is) {
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {

            ArrayList<String> lines = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<TestData> convertLinesToData(List<String> lines) {
        List<TestData> testDataList = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            createTestData(lines, testDataList, i);
        }
        return testDataList;
    }

    private static void createTestData(List<String> lines, List<TestData> testDataList, int i) {
        String line = lines.get(i);
        String[] questionAnswer = line.split(",");
        String question = questionAnswer[0].trim();
        String answer = questionAnswer[1].trim();
        TestData testData = TestData.builder().question(question).rightAnswer(answer).build();
        testDataList.add(testData);
    }

    public InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = TestImportServiceFile.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        return inputStream;
    }
}
