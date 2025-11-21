package com.example.homework.day7;

import java.io.*;

public class CountCharactersTask {

    public void countCharacters(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            int count = 0;
            int c;


            while ((c = reader.read()) != -1) {
                count++;
            }

            reader.close();

            System.out.println("Количество символов в файле '" + filePath + "': " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CountCharactersTask task = new CountCharactersTask();
        task.countCharacters("bottle_output.txt");
    }
}
