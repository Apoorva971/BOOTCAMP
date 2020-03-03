//Override the default method of the interface.
package main.java.com.learning.day5;

public class Q7_Override implements OverridenInterface {
    @Override
    public void ToBeOverridden() {
        System.out.println("Function Overrided");
    }

    public static void main(String[] args) {
        Q7_Override q7_override = new Q7_Override();
        q7_override.ToBeOverridden();//Overriding the default method

    }
}

interface OverridenInterface {
    default void ToBeOverridden() {
        System.out.println("Function to be Overridden");
    }
}