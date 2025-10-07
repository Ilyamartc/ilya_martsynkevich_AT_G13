package homework.day7;

import java.util.Arrays;
import java.util.List;

public class WaterRunner {

    public static void main(String[] args) {
        List<Water> water = Arrays.asList(
                new Water("Прозрачная", "Нет"),
                new Water("Прозрачная", "Нет"),
                new Water("Мутная", "Аммиачный")
        );

        for (Water w : water) {
            System.out.println(w.getColor() + " - " + w.getSmell());
        }
    }
}
