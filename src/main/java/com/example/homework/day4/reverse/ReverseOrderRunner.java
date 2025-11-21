package com.example.homework.day4.reverse;

public class ReverseOrderRunner {
    public static void main(String [] args) {
        ReverseOrder reverseOrder = new ReverseOrder();
        int [] array = {1123,232,113,42131,5123,6234,1237,2348,324,123412430};
        int [] result = reverseOrder.filterAndReverse(array, 3);
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}