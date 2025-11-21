package com.example.classwork;

import java.util.ArrayList;
import java.util.List;

public class Zadacha {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String sentence = "Мама мыла раму мыла";
        String[] words = sentence.split(" ");
        for (String word : words) {
            list.add(word);
        }

        System.out.println("IND:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("IND " + i + ": " + list.get(i));
        }

        System.out.println("IND2:");
        for (String word : list) {
            System.out.println(word);
        }
    }
}