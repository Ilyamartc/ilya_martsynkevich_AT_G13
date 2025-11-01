import java.util.Arrays;
import java.util.List;

public class BirdsExample {
    public static void main(String[] args) {
        List<String> birds = Arrays.asList("Чайка", "Дрозд", "Бусел", "Голубь", "Воробей", "Цапля");

        System.out.println("Птицы в формате --имя--:");
        for (String bird : birds) {
            System.out.println("--" + bird + "--");
        }

        int count = 0;
        String vowels = "аеёиоуыэюяАЕЁИОУЫЭЮЯ";
        for (String bird : birds) {
            int vowelCount = 0;
            for (int i = 0; i < bird.length(); i++) {
                if (vowels.indexOf(bird.charAt(i)) != -1) {
                    vowelCount++;
                }
            }
            if (vowelCount > 1) {
                count++;
            }
        }
        System.out.println("\nКоличество птиц с более чем одной гласной: " + count);

        System.out.println("\nПтицы через пробел:");
        for (int i = 0; i < birds.size(); i++) {
            System.out.print(birds.get(i) + " ");
        }
        System.out.println();

        birds.set(2, "Синица");

        System.out.println("\nПосле замены третьего элемента:");
        for (String bird : birds) {
            System.out.print(bird + " ");
        }
        System.out.println();
    }
}
