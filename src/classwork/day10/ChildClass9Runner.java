package classwork.day10;

import java.util.*;

public class ChildClass9Runner {
    public static void main(String[] args) {
        List<ChildClass9> people = Arrays.asList(
                new ChildClass9("Вася", 13, ChildClass9.Sex.MAN),
                new ChildClass9("Катя", 28, ChildClass9.Sex.WOMEN),
                new ChildClass9("Боба", 24, ChildClass9.Sex.MAN),
                new ChildClass9("Маша", 35, ChildClass9.Sex.WOMEN),
                new ChildClass9("Роман Петрович", 72, ChildClass9.Sex.MAN)
        );

        long countAge18to55 = people.stream()
                .filter(p -> p.age >= 18 && p.age <= 55).count();
        System.out.println("Количество людей от 18 до 55 лет: " + countAge18to55);

        long countMen = people.stream().filter(p -> p.sex == ChildClass9.Sex.MAN).count();
        System.out.println("Количество мужчин: " + countMen);

        List<ChildClass9> sortedPeople = people.stream().sorted((p1, p2) -> {
                    int sexCompare = p1.sex.compareTo(p2.sex);
                    if (sexCompare != 0) return sexCompare;
                    return Integer.compare(p1.age, p2.age);
                }).peek(System.out::println).toList();
        System.out.println("Отсортированная коллекция: " + sortedPeople.size() + " человек");

        double averageAge = ChildClass9.calculateAverageAge(people);
        System.out.println("Средний возраст: " + averageAge);

        List<String> sortedNames = people.stream().map(p -> p.name).sorted().toList();
        System.out.println("Имена, отсортированные по алфавиту: " + sortedNames);

        List<String> sortedDistinctNames = people.stream().map(p -> p.name).sorted(Comparator.reverseOrder()).distinct().toList();
        System.out.println("Имена, отсортированные в обратном порядке без дубликатов: " + sortedDistinctNames);
    }
}