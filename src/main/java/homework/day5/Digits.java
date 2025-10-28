package homework.day5;

public class Digits {

    public static void printDigits(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("There is no text to print!");
            return;
        }

        String[] parts = text.split(""); // массив символов

        int count = 0;
        for (String s : parts) {
            if (s.equals("0") || s.equals("1") || s.equals("2") ||
                    s.equals("3") || s.equals("4") || s.equals("5") ||
                    s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9")) {
                count++;
            }
        }

        int[] numbers = new int[count];
        int index = 0;

        for (String s : parts) {
            if (s.equals("0")) numbers[index++] = 0;
            else if (s.equals("1")) numbers[index++] = 1;
            else if (s.equals("2")) numbers[index++] = 2;
            else if (s.equals("3")) numbers[index++] = 3;
            else if (s.equals("4")) numbers[index++] = 4;
            else if (s.equals("5")) numbers[index++] = 5;
            else if (s.equals("6")) numbers[index++] = 6;
            else if (s.equals("7")) numbers[index++] = 7;
            else if (s.equals("8")) numbers[index++] = 8;
            else if (s.equals("9")) numbers[index++] = 9;
        }

        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
