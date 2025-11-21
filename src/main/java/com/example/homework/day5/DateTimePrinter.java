package com.example.homework.day5;

import java.time.LocalDateTime;

public class DateTimePrinter {

    public static void printCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        int day = now.getDayOfMonth();
        int month = now.getMonthValue(); // 1-12
        int year = now.getYear();
        int hour = now.getHour();
        int minute = now.getMinute();


        String[] months = {"января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};

        String monthName = months[month - 1];

        System.out.println("Сейчас на дворе: " + day + " " + monthName + ", " + year +
                ", " + hour + " часа " + minute + " минут");
    }
}
