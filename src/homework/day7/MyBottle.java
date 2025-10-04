package homework.day7;

import playground.essence.craft.hand.Bottle;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

class MyBottle extends Bottle implements Serializable {
    public MyBottle() {
        super();
    }
    public void greetAndSave(String input) {
        String message = "Hello, I just got '" + input + "' from you!";
        System.out.println(message);
        try (FileWriter writer = new FileWriter("bottle_output.txt")) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MyBottle bottle = new MyBottle();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter something for Bottle: ");
        String userInput = scanner.nextLine();
        bottle.greetAndSave(userInput);
    }
}