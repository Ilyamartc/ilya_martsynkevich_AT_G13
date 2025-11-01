package homework.day7;

import java.io.*;

public class RemoveVowelsTaskSimple {

    public void removeVowels(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            StringBuilder content = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("[aeiouyаеёиоуыэюя]", "");
                content.append(line).append(System.lineSeparator());
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(content.toString());
            writer.close();

            System.out.println("Гласные удалены и файл обновлен.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RemoveVowelsTaskSimple task = new RemoveVowelsTaskSimple();
        task.removeVowels("bottle_output.txt");
    }
}
