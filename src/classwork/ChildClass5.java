package classwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChildClass5 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("мама", "мыла", "раму", "чисто", "fsdfsd");

        String firstElement = list.stream().findFirst().orElse("Empty");
        System.out.println("First element: " + firstElement);

        String firstMama = list.stream().filter(s -> s.equals("мама")).findFirst().get();
        System.out.println("First Mama: " + firstMama);

        String fifthElement = list.stream().skip(4).findFirst().get();
        System.out.println("Fifth element: " + fifthElement);

        List<String> twoElements = list.stream().skip(2).limit(2).toList();
        System.out.println("Two elements: " + twoElements);

        List<String> uniqueWithM = list.stream().filter(s -> s.contains("м")).distinct().toList();
        System.out.println("Unique with M: " + uniqueWithM);
    }
}
