package main.java.com.learning.day4;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Q3 {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the string");
        String s = sc.nextLine();
        char c[] = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (int i=0;i<c.length;i++)
        {
            if(Character.isWhitespace(c[i])==false) {
                if (map.containsKey(c[i])) {
                    map.put(c[i], map.get(c[i]) + 1);
                } else {
                    map.put(c[i], 1);
                }
            }
        }
        for(Map.Entry m:map.entrySet())
        {
            System.out.println(m.getKey()+" : "+m.getValue());
        }
    }
}
