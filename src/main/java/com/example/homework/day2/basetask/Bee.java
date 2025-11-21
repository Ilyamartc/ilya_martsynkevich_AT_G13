package com.example.homework.day2.basetask;

public class Bee {
    private  String gender;
    private long weight;
    double ratio = (double)weight / 5000;
    public Bee(String gender, long weight) {
        this.gender = gender;
    }
    public Bee(String gender, int weight) {
        this.gender = gender;
        this.weight = weight;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public long  getWeight() {
        return weight;
    }
    public void setWeight(long  weight) {
        this.weight = weight;
    }
    public void printBeeDetails() {
        System.out.println("Я " + gender + " и я легче лося в " + ratio + " раз");
    }
}
