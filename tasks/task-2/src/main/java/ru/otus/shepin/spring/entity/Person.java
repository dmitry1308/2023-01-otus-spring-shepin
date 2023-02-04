package ru.otus.shepin.spring.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {
    String name;
    int    age;

    @Override
    public String toString() {
        return "Person data:\n" + "name = " + name + ", age = " + age;
    }
}
