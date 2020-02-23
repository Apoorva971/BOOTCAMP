package main.java.com.learning.day1;
import java.util.Scanner;
public class Q8_reversestring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str =sc.nextLine();
        StringBuffer sb =new StringBuffer(str);
        System.out.println(sb.reverse());
        System.out.println(sb.delete(4,9));
    }
}