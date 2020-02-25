//Write Java code to define List . Insert 5
// floating point numbers in List, and using an iterator, find the sum of the numbers in List.
package main.java.com.learning.day4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Q1 {
    public static void main(String[] args) {
        List<Float> list = new ArrayList<>();
        list.add(1.5f);
        list.add(7.5f);
        list.add(1.7f);
        list.add(3.5f);
        list.add(2.5f);

        Iterator it = list.iterator();
        float store = 0f;
        float result = 0f;

        while (it.hasNext()) {
            store = (float) it.next();
            result = result + store;
        }
        System.out.println(result);

    }
}
