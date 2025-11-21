package com.example.homework.day6;

import com.example.playground.essence.Flyable;
import com.example.playground.essence.craft.air.Copter;
import com.example.playground.essence.craft.air.Plane;
import com.example.playground.essence.craft.air.Rocket;
import com.example.playground.essence.craft.field.Car;
import com.example.playground.essence.craft.field.Moped;
import com.example.playground.essence.craft.hand.Bottle;

public class GenericMethodsInGenericClassTwoParamsRunner {
    public static void main(String[] args) {

        GenericMethodsInGenericClassTwoParams<String, String> g1 =
                new GenericMethodsInGenericClassTwoParams<>("Hello", "World");
        System.out.println(g1.genericMethodGenArgs("Hello"));
        System.out.println(g1.genericMethodGenArgs("Hello", "World"));
        g1.genericMethodHalfGenArgsForX("HelloX", "TestStringX");
        g1.genericMethodHalfGenArgsForY("HelloY", "TestStringY");

        GenericMethodsInGenericClassTwoParams<String, Integer> g2 =
                new GenericMethodsInGenericClassTwoParams<>("Age", 42);
        System.out.println(g2.genericMethodGenArgs("Age"));
        System.out.println(g2.genericMethodGenArgs("Age", 42));
        g2.genericMethodHalfGenArgsForX("AgeX", "TestX");
        g2.genericMethodHalfGenArgsForY(42, "TestY");

        // Примеры с типами из playground.essence
        Copter copter = new Copter(1, "CopterOne");
        Plane plane = new Plane(2, "PlaneOne");
        Rocket rocket = new Rocket(3, "RocketOne");
        Car car = new Car(4, "BMW");
        Moped moped = new Moped(1, "Honda");
        Bottle bottle = new Bottle(); // без аргументов

        GenericMethodsInGenericClassTwoParams<Flyable, Flyable> g3 =
                new GenericMethodsInGenericClassTwoParams<>(copter, plane);
        System.out.println(g3.genericMethodGenArgs(copter));
        System.out.println(g3.genericMethodGenArgs(copter, plane));
        g3.genericMethodHalfGenArgsForX(copter, "CopterString");
        g3.genericMethodHalfGenArgsForY(plane, "PlaneString");

        GenericMethodsInGenericClassTwoParams<Car, Moped> g4 =
                new GenericMethodsInGenericClassTwoParams<>(car, moped);
        System.out.println(g4.genericMethodGenArgs(car));
        System.out.println(g4.genericMethodGenArgs(car, moped));
        g4.genericMethodHalfGenArgsForX(car, "CarString");
        g4.genericMethodHalfGenArgsForY(moped, "MopedString");

        GenericMethodsInGenericClassTwoParams<Bottle, Rocket> g5 =
                new GenericMethodsInGenericClassTwoParams<>(bottle, rocket);
        System.out.println(g5.genericMethodGenArgs(bottle));
        System.out.println(g5.genericMethodGenArgs(bottle, rocket));
        g5.genericMethodHalfGenArgsForX(bottle, "BottleString");
        g5.genericMethodHalfGenArgsForY(rocket, "RocketString");
    }
}
