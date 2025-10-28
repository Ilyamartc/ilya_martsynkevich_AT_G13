package lesson1.school;

public class Runner {
    public static void main(String[] args) {
        Student student1 = new Student("Vasya", 7, "1A");
        Student student2 = new Student("Alla", 8, "2A");
        Student student3 = new Student("Julia", 9, "3A");
        Student student4 = new Student("Sam", 10, "4A");
        Student[] students =  {student1, student2, student3, student4};
        School school = new School("Grammar school №1", students);
        System.out.println(school);
        school.printAllStudents();
    }
}
