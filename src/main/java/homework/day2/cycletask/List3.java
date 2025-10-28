package lesson1.cycletask;
import java.util.Random;

public class List3 {
    public void rndm(){
        int n = 7;
        int[] num = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            num[i] = rand.nextInt(n); //-- создать пустой массив типа int и размера n = 7 и заполнить его случайными элементами используя Random.nextInt(n)
            System.out.print(num[i] + " ");
        }
//        for (int i = 0; i < n; i++) {
//            System.out.print(num[i] + " ");
//        }
        System.out.println();
    }
}