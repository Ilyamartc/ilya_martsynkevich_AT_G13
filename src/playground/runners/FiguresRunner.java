package playground.runners;

import java.util.stream.Stream;

public class FiguresRunner {
    public static void main(String[] args) {
        try {
            Stream.of("Овал", "Прямоугольник", "Круг", "Квадрат", "Эллипс")
                    .map(String::length) .filter(n -> n > 4).forEach(System.out::println);
        } catch (Exception e) {
            System.out.printf("%s: %s%n",e.getClass().getSimpleName(),"Ошибка при обработке списка фигур — возможно, неверные данные или пустой поток.");
        }
    }
}
