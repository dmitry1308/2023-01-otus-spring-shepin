package ru.otus.shepin.spring.service.printService;

public class PrintStringService implements PrintService<String> {
    public void print(String data) {
        System.out.println(data);
    }
}
