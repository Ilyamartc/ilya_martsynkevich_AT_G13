package classwork;

import java.util.*;

public class ChildClass1 {
    public static void main(String[] args) {
        Set<String> mySet1 = new HashSet<>();
        Set<String> mySet2 = new TreeSet<>();

        for (int i = 0; i < 10000000; i++) {
            mySet1.add("string" + i);
            mySet2.add("string" + i);
        }
        long t0 = System.currentTimeMillis();

        Iterator<String> it = mySet1.iterator();
        while (it.hasNext()) {
            it.next();
        }
        System.out.println(System.currentTimeMillis() - t0);
        t0 = System.currentTimeMillis();
        Iterator<String> it1 = mySet2.iterator();
        while (it1.hasNext()) {
            it1.next();
        }
        System.out.println(System.currentTimeMillis() - t0);
    }
}