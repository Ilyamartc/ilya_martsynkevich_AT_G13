package com.example.independentwork.calculator;

public class CalculatorDbl {
    public int[] array;

    public CalculatorDbl(int[] array) {
        this.array = array;
    }

    public int countEvenMultiplications() {
        int dbl = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int product = array[i] * array[j];

                if (product % 2 == 0) {
                    dbl++;
                    System.out.println(array[i] + " * " + array[j] + " = " + product + " ✅");
                }
            }
        }

        return dbl;
    }
}