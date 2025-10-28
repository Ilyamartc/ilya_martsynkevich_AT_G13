package classwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Zadacha1 {
    public  static void main(String[] args) {
        List<String> myList1 = new ArrayList<>();
        List<String> myList2 = new LinkedList<>();

        String baseString = "Test";

        for (int i = 0; i < 10000000; i++) {
            myList1.add(baseString + i);
            myList2.add(baseString + i);
        }
        long t0 = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            myList1.get(i);
        }
        System.out.println("TIME DATA: " + (System.currentTimeMillis() - t0));

        t0 = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            myList2.get(i);
        }
        System.out.println("TIME DATA: " + (System.currentTimeMillis() - t0));

    }
}
