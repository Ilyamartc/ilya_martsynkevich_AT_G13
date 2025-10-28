package homework.day7;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountAndSaveCharactersTask {

    public void countAndSaveCharacters(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int count = 0;
            while (reader.read() != -1) {
                count++;
            }
            reader.close();

            String dateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String newFileName = dateTime + "_" + count + ".txt";

            BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
            writer.write("Количество символов в файле '" + filePath + "': " + count);
            writer.close();

            System.out.println("Результат записан в файл: " + newFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CountAndSaveCharactersTask task = new CountAndSaveCharactersTask();
        task.countAndSaveCharacters("bottle_output.txt");
    }
}
