package com.example.homework.day4.reverse;

public class ReverseOrder {
    public int [] filterAndReverse(int [] array, int n){
        int count = 0;
        for (int value : array){
            if (value > n){
                count++;
            }
        }
        int [] result = new int[count];

        int index = count - 1;
        for (int value : array){
            if (value > n){
                result[index] = value;
                index--;
            }
        }
        return result;
    }
}