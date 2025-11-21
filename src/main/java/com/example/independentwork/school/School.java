package com.example.independentwork.school;

public class School {
    private String schoolName;
    Student[] students;
    public School(String schoolName, Student[] students){
        this.schoolName = schoolName;
        this.students = students;
    }
    public void enrollStudents(Student[] students){
        this.students = students;
    }
    public void printAllStudents(){
        System.out.println("Ученики школы \"" + schoolName + "\":");
        for (Student student : students){
            student.introduce();
        }
    }
    @Override
    public String toString(){
        return "Школа: " + schoolName + ", количество учеников: " + (students != null ? students.length : 0);
    }
}
