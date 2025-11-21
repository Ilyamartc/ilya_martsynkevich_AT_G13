package com.example.independentwork.doubleTask;

public class Car {
    private String nameCar ;
    private String colorCar;
    private Wheel []  wheels;
    public Car(String nameCar, String colorCar, Wheel [] wheels) {
        this.nameCar = nameCar;
        this.colorCar = colorCar;
        this.wheels = wheels;
    }
    public String getNameCar() {
        return nameCar;
    }
    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }
    public String getColorCar() {
        return colorCar;
    }
    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }
    public Wheel[] getWheels() {
        return wheels;
    }
    public void setWheels(Wheel[] wheels) {
        this.wheels = wheels;
    }
    public void installWheels(Wheel[] wheels){
        this.wheels = wheels;
    }
    public void drive() {
        if (wheels != null && wheels.length == 4) {
            for (Wheel wheel : wheels) {
                wheel.inflate();
            }
            System.out.println(nameCar + " started!");
        } else {
            System.out.println("You need all 4 wheels!!");
        }
    }

}
