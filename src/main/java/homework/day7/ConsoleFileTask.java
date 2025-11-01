package homework.day7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleFileTask {
    public void greetAndSave(String input){
        String message = "Hello, I just got '" + input + "' from you!";
        System.out.println(message);
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ConsoleFileTask task = new ConsoleFileTask();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter something: ");
        String userInput = scanner.nextLine();
        task.greetAndSave(userInput);
    }
}
