package lesson1.carShowroom;

public class Car {
    private String brand;
    private String model;
    private int year;

    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    public void showCarInfo(){
        System.out.println("Это : " + brand + " " + model + " " + year + " года");
    }
}
