package com.example.independentwork.school;

public class Student {
    private String name;
    private int age;
    private String grade;

    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
    public void introduce(){
        System.out.println("Меня зовут " + name + ", мне " + age + ", я учусь в " + grade + "  классе.");
    }
}
