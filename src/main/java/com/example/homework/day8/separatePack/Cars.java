package com.example.homework.day8.separatePack;

import java.util.Objects;

public class Cars {
    private String brand;
    private String model;

    public Cars(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Cars{brand='" + brand + "', model='" + model + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cars)) return false;
        Cars cars = (Cars) o;
        return Objects.equals(brand, cars.brand) && Objects.equals(model, cars.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }
}