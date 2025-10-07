import java.util.ArrayList;
import java.util.List;

public class NoodlesExample {
    public static void main(String[] args) {
        List<String> noodles = new ArrayList<>();

        noodles.add("Hakka");
        noodles.add("Ramen");
        noodles.add("Hibachi");
        noodles.add("Schezwan");

        System.out.println("Макароны через тире:");
        boolean first = true;
        for (String noodle : noodles) {
            if (!first) {
                System.out.print(" - ");
            }
            System.out.print(noodle);
            first = false;
        }
        System.out.println();

        for (int i = 0; i < noodles.size(); i++) {
            String updated = noodles.get(i)
                    .replace('a', 'o')
                    .replace('A', 'O');
            noodles.set(i, updated);
        }

        System.out.println("\nПосле замены букв:");
        for (int i = 0; i < noodles.size(); i++) {
            System.out.print(noodles.get(i) + " ");
        }
        System.out.println();
    }
}
