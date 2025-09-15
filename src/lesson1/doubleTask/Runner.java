package lesson1.doubleTask;

public class Runner {
    public static void main(String[] args) {
        Wheel[] wheels1 = {
                new Wheel("rubber"),
                new Wheel("rubber"),
                new Wheel("rubber"),
//                new Wheel("rubber")
        };

        Wheel[] wheels2 = {
                new Wheel("rubber"),
                new Wheel("rubber"),
                new Wheel("rubber"),
                new Wheel("rubber")
        };

        Car car1 = new Car("Toyota", "Red", wheels1);
        Car car2 = new Car("Ford", "Silver", wheels2);

        System.out.println("Let's GO!");
        car1.drive();
        car2.drive();
    }
}
