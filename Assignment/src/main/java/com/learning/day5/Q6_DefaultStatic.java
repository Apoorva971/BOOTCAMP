//Create and access default and static method of an interface.

package main.java.com.learning.day5;

public class Q6_DefaultStatic implements StaticInterface {
    public static void main(String[] args) {
        Q6_DefaultStatic q6_defaultStatic = new Q6_DefaultStatic();
        //accessing default function of interface
        q6_defaultStatic.show1();
        //accessing static funtion of interface
        StaticInterface.show();
    }
}

interface StaticInterface {
    static void show() {
        System.out.println("hi");
    }

    default void show1() {
        System.out.println("hello");
    }
}
