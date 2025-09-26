package homework.day5;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class VowelLetters {

    public static void generateVowelDate(String text) {

        if (text == null || text.isEmpty()) {
            System.out.println("Empty text");
            return;
        }

        String vowels = "аеёиоуыэюяАЕЁИОУЫЭЮЯ";
        int vowelsCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (vowels.indexOf(c) != -1) {
                vowelsCount++;
            }
        }

        LocalDate generatedDate = LocalDate.now().plusDays(vowelsCount);

        int day = generatedDate.getDayOfMonth();
        String month = generatedDate.getMonth()
                .getDisplayName(TextStyle.FULL, new Locale("ru"));

        System.out.println("Сгенерированная гласная дата: " + day + " " + month);
    }

    public static void main(String[] args) {
        String text = "Привет, как дела?";
        generateVowelDate(text);
    }
}
