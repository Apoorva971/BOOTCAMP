package main.java.com.learning.day3;

import javax.swing.*;

class first extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("TTN");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class second extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("work");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Q6_sleepjoin {
    public static void main(String[] args) throws InterruptedException {

        first f1 = new first();
        second s1 = new second();
        f1.start();
        s1.start();
        f1.join();
        s1.join();
    }

}
