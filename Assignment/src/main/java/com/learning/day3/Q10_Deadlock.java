package main.java.com.learning.day3;

public class Q10_Deadlock {
    final Object lock = new Object();
    final Object lock1 = new Object();
    Thread t1 = new Thread() {
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println("acquired lock");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("waiting for lock1");
                    synchronized (lock1) {
                        System.out.println("hello here");
                    }
                }
            }
        }

    };
    Thread t2 = new Thread() {
        public void run() {
            while (true) {
                synchronized (lock) {

                    System.out.println("acquired lock1");

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("waiting for lock");
                    synchronized (lock1) {
                        System.out.println("hey");
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        Q10_Deadlock q10_deadlock = new Q10_Deadlock();
        q10_deadlock.t1.start();
        q10_deadlock.t2.start();
    }
}