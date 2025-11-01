package independentwork.table;

public class Paskal {
    public static void main(String[] args) {
        int n = 5; // число строк
        int[][] triangle = new int[n][n];

        // заполняем треугольник
        for (int i = 0; i < n; i++) {
            triangle[i][0] = 1;       // первая цифра в строке
            triangle[i][i] = 1;       // последняя цифра в строке
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
            }
        }

        // выводим
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
    }
}