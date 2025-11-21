package com.example.homework.day9;
import java.util.*;

    class ChildClass {
        public static void main(String[] args) {
            List<Person> people = Arrays.asList(
                    new Person("Вася", 13, Person.Sex.MAN),
                    new Person("Катя", 28, Person.Sex.WOMEN),
                    new Person("Боба", 24, Person.Sex.MAN),
                    new Person("Маша", 35, Person.Sex.WOMEN),
                    new Person("Роман Петрович", 72, Person.Sex.MAN)
            );

            long countAge18to55 = people.stream()
                    .filter(p -> p.age >= 18 && p.age <= 55).count();
            System.out.println("Количество людей от 18 до 55 лет: " + countAge18to55);

            long countMen = people.stream().filter(p -> p.sex == Person.Sex.MAN).count();
            System.out.println("Количество мужчин: " + countMen);
        }
    }