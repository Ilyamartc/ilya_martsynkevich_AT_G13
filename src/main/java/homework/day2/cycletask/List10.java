package lesson1.cycletask;
import java.util.Arrays;
public class List10 {
    public void sort() {
        int[] array = {1234, 2235, 45343, 4436346, 234325, 6345345, 7234, 832423, 9124124, 1045345};
        Arrays.sort(array);
        for (int i = array.length - 1; i >=0;  i--) {   //-- отсортировать элементы массива в порядке убывания (любым способом) и вывести результат в консоль
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}