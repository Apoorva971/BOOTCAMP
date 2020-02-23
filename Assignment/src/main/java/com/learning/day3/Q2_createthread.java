package main.java.com.learning.day3;
//creating thread using thread class
class firsttype extends Thread{
    public void run()
    {
        for(int i=0;i<10;i++){
            System.out.println("TTN");
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//creating thread using runable interface
class secondtype implements Runnable
{ @Override
    public void run() {
    for(int i=0;i<10;i++){
        System.out.println("apoorva");
    }
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}
public class Q2_createthread {
    public static void main(String[] args) {
        firsttype f1 = new firsttype();
        Thread t1 = new Thread(new secondtype());
        f1.start();//thread of thread class
        t1.start();//thread of runnable interface
        try {
            f1.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
