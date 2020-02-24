//Write a method that takes a string and returns the number of unique characters in the string.
package main.java.com.learning.day4;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Q2 {
    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter string");
        String s = sc.nextLine();
        char c[] = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < c.length; i++) {
            if (Character.isWhitespace(c[i]) == false) {
                if (map.containsKey(c[i])) {
                    map.put(c[i], map.get(c[i]) + 1);
                } else {
                    map.put(c[i], 1);
                }
            }
        }
        for (Map.Entry m : map.entrySet()) {
            if ((int) m.getValue() == 1) {
                System.out.println(m.getKey() + " : " + m.getValue());
                count++;
            }

        }
        System.out.println("count of unique elements is :" + count);
    }
}

