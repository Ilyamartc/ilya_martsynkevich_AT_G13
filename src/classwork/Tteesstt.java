package classwork;

import java.util.Scanner;

public class Tteesstt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String incoming = scanner.nextLine();
            if (incoming.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.printf("Just got %s text!", incoming);
        }
    }
    }