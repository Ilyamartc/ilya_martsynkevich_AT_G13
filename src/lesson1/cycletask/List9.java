package lesson1.cycletask;

public class List9 {
    public void metod() {
        int[] array = {1234, 2235, 45343, 4436346, 234325, 6345345, 7234, 832423, 9124124, 1045345};
        int start = array[0];
        array[0] = array[array.length - 1]; //-- поменять местами первый и последний элементы и вывести результат в консоль
        array[array.length - 1] = start;
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}