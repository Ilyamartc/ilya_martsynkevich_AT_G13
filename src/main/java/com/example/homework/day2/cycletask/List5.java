package com.example.homework.day2.cycletask;

public class List5 {
    public void revers(){
        int [] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i = array.length - 1; i >= 0; i-- ) { //-- вывести в консоль все элементы массива в обратном порядке
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
