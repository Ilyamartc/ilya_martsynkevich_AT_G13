package com.example.playground.essence.people;

public class ManualEngineer extends Engineer {

    public ManualEngineer(int age, int experience) {
        super(age, experience * 2, experience);
    }
}
