package homework.day10;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class PeopleRunner {
    public static void main(String[] args) {
        Stream<Person> people = Stream.of(
                new Person(32, "Коля"),
                new Person(24, "Оля"),
                new Person(55, "Вася"),
                new Person(63, "Маша")
        );

        try {
            double averageAge = people.filter(p -> p.getAge() < 60)
                    .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                    .map(p -> new Person(p.getAge() + 4, p.getName()))
                    .mapToInt(Person::getAge)
                    .average()
                    .orElse(0);

            try (FileWriter writer = new FileWriter("average_age.txt")) {
                writer.write("Average age: " + averageAge);
            }

            System.out.println("Средний возраст записан в файл average_age.txt");

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
