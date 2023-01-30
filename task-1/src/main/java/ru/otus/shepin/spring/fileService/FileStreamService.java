package ru.otus.shepin.spring.fileService;

import ru.otus.shepin.spring.utils.FileResourcesUtils;

import java.io.InputStream;

public class FileStreamService implements FileService {
    @Override
    public InputStream getFileAsStream(String fileName) {
       return FileResourcesUtils.getFileFromResourceAsStream(fileName);
    }
}
