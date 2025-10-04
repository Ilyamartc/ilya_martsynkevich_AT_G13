package homework.day8;

import java.util.ArrayList;
import java.util.List;

public class ButterfliesTask {

    public static void main(String[] args) {

        try {
            List<String> butterflies = new ArrayList<>();
            butterflies.add("Common blue");
            butterflies.add("Swallowtail");
            butterflies.add("Aglais io");
            butterflies.add("Common blue");

            System.out.println("Бабочки в кавычках:");
            for (String butterfly : butterflies) {
                System.out.println("\"" + butterfly + "\"");
            }

            long countO = butterflies.stream().filter(b -> b.toLowerCase().contains("о") || b.toLowerCase().contains("o")).count();
            System.out.println("Количество бабочек с буквой 'о': " + countO);

            System.out.println("По индексу через пробел:");
            for (int i = 0; i < butterflies.size(); i++) {
                System.out.print(butterflies.get(i) + " ");
            }
            System.out.println();

            System.out.println("Через for-each с переносом строки:");
            for (String butterfly : butterflies) {
                System.out.println(butterfly);
            }

        } catch (Exception e) {
            System.out.println("Исключение: " + e.getClass().getSimpleName());
            System.out.println("Причина: " + e.getMessage());
        }

    }
}
