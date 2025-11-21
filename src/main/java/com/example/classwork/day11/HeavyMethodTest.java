package com.example.classwork.day11;

public class HeavyMethodTest {
    public static void heavyMethod() {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < 100_000_000; i++) {
            double d = Math.tan(Math.atan(123456789.123456789));
        }
        System.out.printf("I am %s, and I have finished in %d millis \r\n",
                Thread.currentThread().getName(),
                (System.currentTimeMillis() - t0));
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> heavyMethod());
        Thread t2 = new Thread(() -> heavyMethod());
        Thread t3 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            heavyMethod();
        });

        t1.start();
        t2.start();
        t3.start();
    }
}