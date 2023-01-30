package ru.otus.shepin.spring.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;


@Getter
@Setter
@AllArgsConstructor
public class FileResourcesUtils {
    private String fileName;

    public static InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        return inputStream;
    }
}
