package homework.day10;

import java.util.*;
import java.util.stream.*;
public class NumbersModRunner {
    public static void main(String[] args) {
        Stream<Integer> numbersMod = Stream.of(626, 435, 9, 1463268, 24, 2237, 33, 9090);
        Map<Character, String> digitToWord = Map.of(
                '0', "zero",
                '1', "one",
                '2', "two",
                '3', "three",
                '4', "four",
                '5', "five",
                '6', "six",
                '7', "seven",
                '8', "eight",
                '9', "nine"
        );
        System.out.println("Результат:");
        numbersMod.map(String::valueOf)
                .filter(s -> s.contains("3"))
                .flatMap(s -> s.chars()
                        .mapToObj(c -> digitToWord.get((char) c)))
                .distinct().sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
}