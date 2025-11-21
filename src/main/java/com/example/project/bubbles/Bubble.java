package com.example.project.bubbles;

public class Bubble {
    private final double volume;
    private String gas;

    // Старый конструктор для Bottle
    public Bubble(String gas) {
        this.volume = 0.3; // дефолтный объём
        this.gas = gas;
    }

    // Новый конструктор для потоков и BubblesRunner
    public Bubble(double volume, String gas) {
        this.volume = volume;
        this.gas = gas;
    }

    public double getVolume() {
        return volume;
    }

    public String getGas() {
        return gas;
    }

    public void pop() {
        System.out.println("Cramp! " + gas);
    }

    @Override
    public String toString() {
        return "Bubble{" +
                "volume=" + volume +
                ", gas='" + gas + '\'' +
                '}';
    }
}
