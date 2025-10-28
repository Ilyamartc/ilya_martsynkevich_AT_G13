package homework.day10;

import java.util.Arrays;
import java.util.stream.Stream;

public class ElementsRunner {
    public static void main(String[] args) {
        Stream.of("Text field", "Radio", "Check-box", "Drop-down", "Picker", "Breadcrumb")
                .flatMap(element -> Arrays.stream(element.split(" ")))
                .map(word -> word.length() % 2 != 0 ? word.replace("e", "o") : String.valueOf(word.length()))
                .distinct().forEach(System.out::println);
    }
}