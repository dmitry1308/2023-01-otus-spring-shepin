package ru.otus.shepin.spring.fileService;

import java.io.InputStream;

public interface FileService {
    InputStream getFileAsStream(String fileName);
}
