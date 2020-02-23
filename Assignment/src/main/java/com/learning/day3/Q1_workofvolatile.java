package main.java.com.learning.day3;

public class Q1_workofvolatile {
    private volatile int count=0;
    public static void main(String[] args) {
        Q1_workofvolatile q1_workofvolatile = new Q1_workofvolatile();
        q1_workofvolatile.threadworking();

    }
    public void threadworking()

    {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
    t1.start();
    t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}
