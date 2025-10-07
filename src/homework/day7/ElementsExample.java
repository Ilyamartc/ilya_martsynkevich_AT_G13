import java.util.ArrayList;
import java.util.List;

public class ElementsExample {
    public static void main(String[] args) {
        List<String> elements = new ArrayList<>();

        elements.add("Text field");
        elements.add("Radio");
        elements.add("Check-box");
        elements.add("Drop-down");
        elements.add("Picker");
        elements.add("Breadcrumb");

        System.out.println("Элементы через пробел:");
        for (String element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();

        int count = 0;
        for (String element : elements) {
            if (element.split(" ").length > 1) {
                count++;
            }
        }
        System.out.println("\nКоличество элементов с более чем одним словом: " + count);

        System.out.println("\nЭлементы по индексу через пробел:");
        for (int i = 0; i < elements.size(); i++) {
            System.out.print(elements.get(i) + " ");
        }
        System.out.println();

        elements.add(3, "Spinner");

        elements.remove(1);

        elements.set(4, "Switch");

        System.out.println("\nПосле изменений:");
        for (String element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
