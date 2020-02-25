//Create a functional interface whose method takes 2 integers and return one integer.
package main.java.com.learning.day5;

import org.w3c.dom.ls.LSOutput;

public class Q2_GetintReturnint {
    public static void main(String[] args) {
        ReturnInt returnInt = (a, b) -> {
            int c = a + b;
            return (c);
        };
        System.out.println(returnInt.returnvalue(4, 6));
    }

}

@FunctionalInterface
interface ReturnInt {
    int returnvalue(int a, int b);
}
