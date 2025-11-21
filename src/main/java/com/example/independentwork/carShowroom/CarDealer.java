package com.example.independentwork.carShowroom;

public class CarDealer {
    private String dealerName;
    Car [] cars;
    public CarDealer(String dealerName) {
        this.dealerName = dealerName;
    }
    public void addCars(Car[] cars){
        this.cars = cars;
    }
    public void printAllCars(){
        System.out.print("Салон:  " + dealerName);
        if(cars != null) {
            for (Car car : cars){
                car.showCarInfo();
            }
        }
        else {
            System.out.println("Нет машин");
        }
    }
}
