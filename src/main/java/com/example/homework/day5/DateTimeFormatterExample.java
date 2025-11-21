package com.example.homework.day5;

public class DateTimeFormatterExample {

    public static void formatDateTime(String input) {

        String[] parts = input.split(" ");
        String[] time = parts[0].split("\\.");
        String[] date = parts[1].split("\\.");

        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        String year = date[2];

        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        String monthName = months[month - 1];

        System.out.println(monthName + ", " + day + ", " + year + " " + time[0] + ":" + time[1]);
    }
}
