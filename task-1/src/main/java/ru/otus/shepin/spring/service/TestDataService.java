package ru.otus.shepin.spring.service;

import lombok.AllArgsConstructor;
import ru.otus.shepin.spring.entity.TestData;
import ru.otus.shepin.spring.fileService.FileService;
import ru.otus.shepin.spring.utils.DataHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class TestDataService {
    private FileService fileService;

    List<TestData> prepareDataTest(String fileName) {
        InputStream fileAsStream = fileService.getFileAsStream(fileName);
        List<String> lines = getLines(fileAsStream);
        return DataHelper.convertLinesToData(lines);
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
}
