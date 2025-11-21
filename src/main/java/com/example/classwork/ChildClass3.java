package com.example.classwork;

import java.util.*;

public class ChildClass3 {
    public static void main(String[] args) {
        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Black");
        colors.add("Orange");
        colors.add("Green");
        colors.add("White");


        for (String color : colors) {
            System.out.print(color + " ");
        }
        System.out.println();
        colors.add(2, "Pink");
        colors.add(4, "Yellow");

        for (int i  = 0; i < colors.size(); i++) {
            System.out.print(colors.get(i) + " ");
        }
        System.out.println();


    }
}