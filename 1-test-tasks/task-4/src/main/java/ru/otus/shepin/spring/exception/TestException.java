package ru.otus.shepin.spring.exception;

public class TestException  extends RuntimeException{
    public TestException(String message, Throwable cause) {
        super(message, cause);
    }
}
