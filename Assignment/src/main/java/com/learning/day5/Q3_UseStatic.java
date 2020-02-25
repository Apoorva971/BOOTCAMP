//Using (instance) Method reference create and apply add and subtract method
// and using (Static) Method reference create and
// apply multiplication method for the functional interface created.
package main.java.com.learning.day5;

@FunctionalInterface
interface GenericInterface {
    void manupulation(int a, int b);
}

class UseStatic {
    static void sum(int a, int b) {
        System.out.println("sum is: " + (a + b));
    }

    static void sub(int a, int b) {
        if (a > b)
            System.out.println("difference is: " + (a - b));
        else
            System.out.println("difference is: " + (b - a));
    }

    void mult(int a, int b) {
        System.out.println("product is: " + (b * a));
    }
}

public class Q3_UseStatic {
    public static void main(String[] args) {
        GenericInterface genericInterface = UseStatic::sum;
        GenericInterface genericInterface1 = UseStatic::sub;
        GenericInterface genericInterface2 = new UseStatic()::mult;
        genericInterface.manupulation(10, 20);
        genericInterface1.manupulation(10, 40);
        genericInterface2.manupulation(5, 4);
    }
}

