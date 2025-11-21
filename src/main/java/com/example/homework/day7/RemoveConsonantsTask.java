package com.example.homework.day7;

import java.io.*;

public class RemoveConsonantsTask {

    public void removeConsonantsAppend(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder originalContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                originalContent.append(line).append(System.lineSeparator());
            }
            reader.close();

            String consonantsRemoved = originalContent.toString().replaceAll("[bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ]", "");

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.newLine();

            writer.write(consonantsRemoved);

            writer.close();

            System.out.println("Согласные удалены и текст добавлен в конец файла.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RemoveConsonantsTask task = new RemoveConsonantsTask();
        task.removeConsonantsAppend("bottle_output.txt");
    }
}
