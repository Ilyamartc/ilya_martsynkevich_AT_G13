package com.example.classwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Zadacha2 {
    public static void main(String[] args) {

        List<String> myList1 = new ArrayList<>();
        List<String> myList2 = new LinkedList<>();

        // Заполняем списки 10,000,000 строками
        String baseString = "Test";
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            myList1.add(baseString + i);
            myList2.add(baseString + i);
        }
        long t1 = System.currentTimeMillis();
        System.out.println("Время заполнения (мс): " + (t1 - t0));

        // Тестируем доступ по индексу для ArrayList
        t0 = System.currentTimeMillis();
        for (int i = 0; i < myList1.size(); i++) {
            String temp = myList1.get(i);
        }
        t1 = System.currentTimeMillis();
        System.out.println("Время доступа по индексу для ArrayList (мс): " + (t1 - t0));

        // Тестируем доступ по индексу для LinkedList
        t0 = System.currentTimeMillis();
        for (int i = 0; i < myList2.size(); i++) {
            String temp = myList2.get(i);
        }
        t1 = System.currentTimeMillis();
        System.out.println("Время доступа по индексу для LinkedList (мс): " + (t1 - t0));
    }
}