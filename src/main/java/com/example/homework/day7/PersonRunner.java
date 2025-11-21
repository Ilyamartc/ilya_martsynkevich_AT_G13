package com.example.homework.day7;

import java.util.ArrayList;
import java.util.List;

public class PersonRunner {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Коля", 32));
        people.add(new Person("Оля", 24));
        people.add(new Person("Вася", 55));
        people.add(new Person("Маша", 63));

        for (Person person : people) {
            System.out.print(person.getAge() + " ");
        }

        System.out.println();

        for (Person person : people) {
            System.out.print(person.getName() + " ");
        }

        System.out.println();

        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }
}
