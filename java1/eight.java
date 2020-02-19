//Write a program to reverse a string and remove character from index 4 to index 9 from the reversed string using String Buffer

import java.util.Scanner;

public class eight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str =sc.nextLine();
        StringBuffer sb =new StringBuffer(str);
        System.out.println(sb.reverse());
        System.out.println(sb.delete(4,9));
    }
}
