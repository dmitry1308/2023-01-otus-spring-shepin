package ru.otus.shepin.spring.service.printService;

import org.springframework.stereotype.Service;

@Service
public class PrintStringService implements PrintService<String> {
    public void print(String data) {
        System.out.println(data);
    }
}
