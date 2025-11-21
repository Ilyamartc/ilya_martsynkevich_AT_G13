package com.example.homework.day4.sum;

public class SumArray {
    public int sumArray(int [] array){
        int length=array.length;
        while(length>=10){
            length /= 10;
        }
        int firstDigit = length;
        int sum=0;
        for(int i : array){
            if(i%firstDigit==0){
                sum+=i;
            }
        }
        return sum;
    }
}