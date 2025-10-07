package homework.day8.separatePack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitRunner {
    public static void main(String[] args) {
        List<Fruits> fruits = new ArrayList<>();

        fruits.add(new Fruits("Яблоко", 120));
        fruits.add(new Fruits("Банан", 80));
        fruits.add(new Fruits("Груша", 150));
        fruits.add(new Fruits("Апельсин", 200));

        System.out.println("Имена фруктов:");
        for (Fruits f : fruits) {
            System.out.print(f.getName() + " ");
        }
        System.out.println();

        System.out.println("Вес фруктов:");
        for (Fruits f : fruits) {
            System.out.print(f.getWeight() + " ");
        }
        System.out.println();

        Map<Integer, Fruits> fruitMap = new HashMap<>();

        fruitMap.put(1, fruits.get(0));
        fruitMap.put(2, fruits.get(1));
        fruitMap.put(3, fruits.get(2));
        fruitMap.put(4, fruits.get(3));

        System.out.println("Ключи карты:");
        for (Integer key : fruitMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println("Значения карты:");
        for (Fruits value : fruitMap.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Пары ключ-значение:");
        for (Map.Entry<Integer, Fruits> entry : fruitMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}