package classwork.day10;

import java.util.stream.Stream;

public class NumbersRunner {
    public static void main(String[] args) {
        // создаём поток чисел
        Stream<Integer> numbers = Stream.of(3342, 34, 79, 23426, 68, 1324, 55, 7699);
        
        System.out.println("Исходные числа:");
        numbers.peek(System.out::println) // печатаем каждое
                .toList(); // завершаем стрим (peek без терминального не выполнится)

        System.out.println("\nОтсортированные числа:");
        Stream.of(3342, 34, 79, 23426, 68, 1324, 55, 7699)
                .sorted()
                .forEach(System.out::println);

        int sum = Stream.of(3342, 34, 79, 23426, 68, 1324, 55, 7699)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("\nСумма всех чисел: " + sum);
    }
}
