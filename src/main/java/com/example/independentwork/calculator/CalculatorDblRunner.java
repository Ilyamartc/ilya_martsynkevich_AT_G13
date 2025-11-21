package com.example.independentwork.calculator;

public class CalculatorDblRunner {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        CalculatorDbl calc = new CalculatorDbl(nums);

        int result = calc.countEvenMultiplications();
        System.out.println("Количество чётных произведений: " + result);
    }
}
