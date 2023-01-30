package ru.otus.shepin.spring.service.fileService;

import java.io.InputStream;

public interface FileService {
    InputStream getFileAsStream(String fileName);
}
