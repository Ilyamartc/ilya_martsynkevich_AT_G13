package playground.runners;

import java.util.ArrayList;

public class CitiesRunner {
    public static void main(String[] args) {
        try {
            var cities = new ArrayList<String>();
            cities.add("Минск");
            cities.add("Москва");
            cities.add("Берлин");

            int totalLetters = cities.stream().mapToInt(String::length).sum();

            System.out.println(totalLetters);
        } catch (Exception e) {
            System.out.printf("%s: %s%n",e.getClass().getSimpleName(),"Ошибка при подсчёте букв — возможно, список пуст или содержит некорректные данные.");
        }
    }
}
