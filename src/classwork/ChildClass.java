package classwork;

import java.util.*;

public class ChildClass {
    public static void main(String[] args) {
        Set <String> mySet = new HashSet<>();
        mySet.add("mama");
        mySet.add("мыла");
        mySet.add("раму");
        mySet.add("мыла");

        Iterator<String> it = mySet.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        for (String word : mySet) {
            System.out.println(word);
        }
    }
}
