package main.java.com.learning.day3;

class synblockmmethod {
    ////Synchronized Method
    synchronized public void getLine() {
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("End of Synchronized Method");
    }

    ////Synchronized Block
    /*public void getLine() {
        synchronized (this) {
            for (int i = 0; i < 4; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(400);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("End of Synchronized BLock");
        }
    }*/
}

class Th extends Thread {

    synblockmmethod line;

    Th(synblockmmethod line) {
        this.line = line;
    }

    @Override
    public void run() {
        line.getLine();
    }
}

public class Q3_synblock {
    public static void main(String[] args) {
        synblockmmethod obj = new synblockmmethod();
        Th th1 = new Th(obj);
        Th th2 = new Th(obj);
        th1.start();
        th2.start();
    }
}
