package com.example.homework.day5;

public class Duplicates {
    public static void printDuplicates(String text) {
        String [] words = text.split(" ");
        for(int i = 0; i < words.length; i++){
            for (int j = i+1; j < words.length; j++){
                if(words[i].equals(words[j])){
                    System.out.println(words[i]);
                    break;
                }
            }
        }
    }
}
