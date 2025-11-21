package com.example.homework.day7;

import java.io.*;

public class DeserializeObjectTask {

    public void readObjectAndSaveToFile(String inputFilePath, String outputFilePath) {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            Object obj = ois.readObject();

            writer.write(obj.toString());

            System.out.println("Объект прочитан из файла и записан в текстовый файл: " + outputFilePath);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeserializeObjectTask task = new DeserializeObjectTask();
        task.readObjectAndSaveToFile("bottle_object.ser", "bottle_toString.txt");
    }
}
