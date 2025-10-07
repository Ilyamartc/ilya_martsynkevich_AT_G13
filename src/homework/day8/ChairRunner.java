package homework.day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChairRunner {
    public static void main(String[] args) {
        List<Chair> furniture = Arrays.asList(
                new Chair(1, 2),
                new Chair(4, 2),
                new Chair(2, 4)
        );

        for (Chair chair : furniture) {
            System.out.print((chair.getHeight() * chair.getWidth()) + " ");
        }
        System.out.println();

        Map<Integer, Chair> chairMap = new HashMap<>();

        chairMap.put(1, furniture.get(0));
        chairMap.put(2, furniture.get(1));
        chairMap.put(3, furniture.get(2));

        for (Integer key : chairMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        for (Chair value : chairMap.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        for (Map.Entry<Integer, Chair> entry : chairMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
