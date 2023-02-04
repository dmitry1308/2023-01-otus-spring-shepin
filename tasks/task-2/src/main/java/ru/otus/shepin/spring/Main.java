package ru.otus.shepin.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.shepin.spring.controller.Controller;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("spring-context.xml");

        Controller controller = context.getBean(Controller.class);
        controller.handle();
    }
}
