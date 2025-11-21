package com.example.classwork;

import java.util.Arrays;
import java.util.List;


public class ChildClass4 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("мама", "мыла", "раму", "чисто");
        System.out.println(list.stream().filter(s ->s.equals("мама:: ")).count());
    }
}
