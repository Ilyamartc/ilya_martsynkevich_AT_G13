package playground.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ButterfliesRunner {
    public static void main(String[] args) {
        try {
            new ArrayList<>(List.of("Common blue", "Swallowtail", "Aglais io", "Common blue"))
                    .stream().map(s -> "\"" + s + "\"").filter(s -> s.toLowerCase().contains("a") && s.toLowerCase().contains("o")).forEach(System.out::println);
        } catch (Exception e) {
            System.out.printf("%s: %s%n", e.getClass().getSimpleName(), "Ошибка при обработке списка бабочек — возможно, null или некорректные данные.");
        }
    }
}
