package com.example.homework.day2.cycletask;

public class List8 {
    public void minima(){
        int [] array = {1234, 2235, 45343, 4436346, 234325, 6345345, 7234, 832423, 9124124, 1045345};
        int min = array[0];
        for(int num:array){
            if(num < min){
                min = num; // -- найти минимальный элемент массива и вывести результат в консоль
            }
        }
        System.out.print("Minimum number is " + min);
        System.out.println();
    }
}
