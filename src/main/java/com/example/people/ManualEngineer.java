package com.example.people;

public class ManualEngineer extends Engineer {
    public ManualEngineer(String name, int experience) {
        super(name, experience);
    }

    @Override
    public String getType() {
        return "Manual";
    }
}
