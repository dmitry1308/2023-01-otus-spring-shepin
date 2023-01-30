package ru.otus.shepin.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.shepin.spring.controller.TestController;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        TestController testController = context.getBean(TestController.class);
        testController.passTest();
    }
}
