import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NumbersExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3342, 34, 79, 23426, 68, 1324, 55, 7699);

        System.out.println("Числа по одному в строке:");
        for (Integer number : numbers) {
            System.out.println(number);
        }

        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        System.out.println("\nСумма всех чисел: " + sum);

        Collections.sort(numbers);

        System.out.println("\nСписок по возрастанию через пробел:");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i) + " ");
        }
        System.out.println();

        Collections.reverse(numbers);

        System.out.println("\nСписок в обратном порядке через пробел:");
        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
