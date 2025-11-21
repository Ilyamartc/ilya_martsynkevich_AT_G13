package com.example.homework.day10;

public class Water {
    private String color;
    private String smell;

    public Water(String color, String smell) {
        this.color = color;
        this.smell = smell;
    }

    public String getColor() {
        return color;
    }

    public String getSmell() {
        return smell;
    }

    @Override
    public String toString() {
        return "Water{color='" + color + "', smell='" + smell + "'}";
    }
}