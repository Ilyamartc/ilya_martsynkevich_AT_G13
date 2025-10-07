package homework.day7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarsExample {
    public static void main(String[] args) {
        List<String> cars = new ArrayList<>();
        cars.add("Мерс");
        cars.add("Ауди");
        cars.add("Жигуль");
        cars.add("Рено");
        cars.add("Жигуль");
        cars.add("Жигуль");
        cars.add("Ауди");

        try {
            for (String car : cars) {
                System.out.println("\"" + car + "\"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Iterator<String> iterator = cars.iterator();

        while (iterator.hasNext()) {
            String car = iterator.next();
            if (car.length() > 4) {
                iterator.remove();
            }
        }
        System.out.println("Оставшиеся авто:");
        for (String car : cars) {
            System.out.print(car + " ");
        }
        System.out.println();
        }
    }