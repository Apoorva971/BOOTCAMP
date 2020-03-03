//Find average of the number inside integer list after doubling it.

package main.java.com.learning.day5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Q11_Average {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //map is used to give the operation
        System.out.println(list.stream().map(e -> e * 2).collect(Collectors.averagingDouble(e -> e)));
    }
}
