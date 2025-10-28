package homework.day8.separatePack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarsRunner {
    public static void main(String[] args) {
        List<Cars> cars = new ArrayList<>();

        cars.add(new Cars("Toyota", "Corolla"));
        cars.add(new Cars("Ford", "Mustang"));
        cars.add(new Cars("BMW", "X5"));

        System.out.println("Список автомобилей:");
        for (Cars car : cars) {
            System.out.println(car.getBrand() + ":" + car.getModel());
        }

        Map<String, Cars> carMap = new HashMap<>();

        for (Cars car : cars) {
            carMap.put(car.getBrand(), car);
        }

        System.out.println("\nКлючи карты:");
        for (String key : carMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("\nЗначения карты:");
        for (Cars value : carMap.values()) {
            System.out.println(value);
        }

        System.out.println("\nПары ключ-значение:");
        for (Map.Entry<String, Cars> entry : carMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
