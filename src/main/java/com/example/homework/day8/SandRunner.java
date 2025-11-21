package com.example.homework.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SandRunner {
    public static void main(String[] args) {
        List<Sand> sandbox = new ArrayList<>();

        sandbox.add(new Sand(2, "Речной"));
        sandbox.add(new Sand(4, "Речной"));
        sandbox.add(new Sand(2, "Карьерный"));
        sandbox.add(new Sand(7, "Речной"));

        for (Sand sand : sandbox) {
            System.out.print(sand.getWeight() + " ");
        }
        System.out.println();

        for (Sand sand : sandbox) {
            System.out.print(sand.getName() + " ");
        }
        System.out.println();

        Map<Integer, Sand> sandMap = new HashMap<>();

        sandMap.put(101, sandbox.get(0));
        sandMap.put(102, sandbox.get(1));
        sandMap.put(103, sandbox.get(2));
        sandMap.put(104, sandbox.get(3));

        for (Integer key : sandMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        for (Sand value : sandMap.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        for (Map.Entry<Integer, Sand> entry : sandMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
