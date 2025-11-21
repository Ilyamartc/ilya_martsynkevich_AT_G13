package com.example.independentwork.carShowroom;

public class Runner {
    public static void main(String[] args) {
        Car car1 = new Car("Ford", "Scorpio", 1986);
        Car car2 = new Car("Ford", "Escort", 1982);
        Car [] cars = {car1,car2};

        CarDealer carDealer = new CarDealer("Ford Krakow: ");
        carDealer.addCars(cars);

        carDealer.printAllCars();
    }
}
