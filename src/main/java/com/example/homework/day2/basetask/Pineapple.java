package com.example.homework.day2.basetask;

public class Pineapple {
    private String grade;
    private float  heatCapacity;
    public Pineapple(String grade, float  heatCapacity) {
        this.grade = grade;
        this.heatCapacity = heatCapacity;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public float  getHeatCapacity() {
        return heatCapacity;
    }
    public void setHeatCapacity(int heatCapacity) {
        this.heatCapacity = heatCapacity;
    }
    public void printPineappleDetails() {
        if (heatCapacity > 2140) {
            System.out.println("Я ананас, теплоемкость которого " + grade + ", чем у ветчины");
        }
        else {
            System.out.println("В ветчине тепла запасется \" + grade + \" :(");
        }
    }
}
