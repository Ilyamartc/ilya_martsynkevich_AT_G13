package com.example.homework.day4.everysecondnumber;

public class EachNumbers {
    public int evenNumbers(int [] arrays, int n){
        int sum=0;
        for(int i = n -1 ;i <= arrays.length - 1; i += n){
            sum+=arrays[i];
        }
        return sum;
        }
    }