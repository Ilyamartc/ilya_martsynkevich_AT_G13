package homework.day9;

import java.util.Arrays;

public class BirdsRunner {
    public static void main(String[] args) {
        Arrays.asList("Чайка", "Дрозд", "Бусел", "Голубь", "Воробей", "Цапля")
                .stream()
                .map(bird -> bird.replace('о', 'а')) // заменить "о" на "а"
                .map(String::toLowerCase) // всё в нижний регистр
                .reduce((a, b) -> a + b) // собрать все слова в одну строку
                .ifPresent(result -> Arrays.stream(
                                result.replace("ь", "") // убрать мягкий знак
                                        .split("б")) // разбить по "б"
                        .forEach(part -> System.out.println("--" + part + "--")));
    }
}
