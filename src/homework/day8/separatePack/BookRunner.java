package homework.day8.separatePack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRunner {
    public static void main(String[] args) {
        List<Book> library = new ArrayList<>();

        library.add(new Book("Война и мир", "Толстой"));
        library.add(new Book("Преступление и наказание", "Достоевский"));
        library.add(new Book("Мастер и Маргарита", "Булгаков"));

        System.out.println("Названия книг:");
        for (Book book : library) {
            System.out.print(book.getName() + " ");
        }
        System.out.println();

        Map<String, String> bookMap = new HashMap<>();

        for (Book book : library) {
            bookMap.put(book.getName(), book.getAuthor());
        }

        System.out.println("\nКлючи карты:");
        for (String key : bookMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("\nЗначения карты:");
        for (String value : bookMap.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("\nПары ключ-значение:");
        for (Map.Entry<String, String> entry : bookMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
