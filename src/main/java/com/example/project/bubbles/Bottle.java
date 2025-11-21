package com.example.project.bubbles;

public class Bottle {
    private double volume;
    private SparklingWater water;
    public void open() {
        water.degas();
    }
    public double getVolume() {
        return volume;
    }
    public SparklingWater getWater() {
        return water;
    }
    public Bottle(double volume) {
        this.volume = volume;
        this.water = new SparklingWater("clear", "transparent", "no smell", 20.0);
        int bubblesCount = (int)(volume * 10000); // 1 литр = 10000 пузырьков
        Bubble[] bubbles = new Bubble[bubblesCount];
        for (int i = 0; i < bubblesCount; i++) {
            bubbles[i] = new Bubble("CO2");
        }
        water.pump(bubbles);
    }
}