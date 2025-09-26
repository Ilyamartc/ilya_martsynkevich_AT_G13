package independentwork.table;

public class Table {
    public static void main(String[] args) {
        int n = 3; // размер таблицы
        int value = 1;

        for (int i = 0; i < n; i++) {         // строки
            for (int j = 0; j < n; j++) {     // колонки
                if (value % 2 == 0) {
                    System.out.print(value + " ");
                }
                value++;
            }
            System.out.println();
        }
    }
}