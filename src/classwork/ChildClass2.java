package classwork;

import java.util.HashMap;
import java.util.Map;

public class ChildClass2 {
    public static void main(String[] args) {
        Map<Integer, String> myMap1 = new HashMap<>();

        String[] array = "мама мыла раму мыла".split(" ");

        for (int i = 0; i < array.length; i++) {
            myMap1.put(i, array[i]);
        }
        for(int key : myMap1.keySet()){
            System.out.println("Ключ: " + key + " Значение: "+ myMap1.get(key));
        }

        for(String value : myMap1.values()){
            System.out.println("Значение: " + value);
        }

        for(Map.Entry<Integer, String> entry : myMap1.entrySet()){
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + value);
        }

        System.out.println(myMap1);
    }
}
