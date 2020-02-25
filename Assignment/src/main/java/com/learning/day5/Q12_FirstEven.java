//Find the first even number in the integer list which is greater than 3.
package main.java.com.learning.day5;

import java.util.Arrays;
import java.util.List;

public class Q12_FirstEven {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.stream().filter(e -> e > 3).filter(e -> e % 2 == 0).findFirst().orElse(0));
    }
}
