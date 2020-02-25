package main.java.com.learning.day3;

import java.util.Scanner;

public class Q5_waitnotify {
    public void funone() throws InterruptedException {
        synchronized (this) {
            System.out.println("thread running");
            wait();
            System.out.println("after notification");
        }
    }

    public void funtwo() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("waiting");
            sc.next();
            System.out.println("wait over");
            notify();
            Thread.sleep(2000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Q5_waitnotify q5_waitnotify = new Q5_waitnotify();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    q5_waitnotify.funone();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    q5_waitnotify.funtwo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

