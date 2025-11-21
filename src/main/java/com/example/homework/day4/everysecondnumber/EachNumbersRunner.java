package com.example.homework.day4.everysecondnumber;

import java.util.Arrays;

public class EachNumbersRunner {
    public static void main(String[] args) {
        EachNumbers eachNumbers = new EachNumbers();
        int [] array = {1,2,3,4,5,6,7,8,9,10};
        int result = eachNumbers.evenNumbers(array, 2);
        System.out.println("Сумма каждого второго числа: " + result);
    }
}
