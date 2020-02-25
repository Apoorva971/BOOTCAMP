//Collect all the even numbers from an integer list.

package main.java.com.learning.day5;

import java.util.Arrays;
import java.util.List;

public class Q9_CollectEven {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream().filter(e -> e % 2 == 0).forEach(System.out::println);
    }
}
