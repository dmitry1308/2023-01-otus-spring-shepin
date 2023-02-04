package ru.otus.shepin.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.shepin.spring.controller.Controller;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        Controller controller = context.getBean(Controller.class);
        controller.handle();
    }
}
