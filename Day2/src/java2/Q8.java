/*  read words from the keyboard until the word done is entered. For each word except done, report whether its first character is equal   to  its last character. For the required loop, use a
a)while statement
b)do-while statement */

package java2;

import java.util.Scanner;

public class Q8 {
    //using while loop
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        while (true) {
            text = scanner.nextLine();
            if (text.equalsIgnoreCase("done")) {
                System.out.println("you have entered done ,i am stopping");
                return;
            }
            if (text.charAt(0) == text.charAt(text.length() - 1)) {
                System.out.println("first and last character matched");
            } else {
                System.out.println("first and last not matched");
            }
        }

    }
}

    //using do while loop
    class Q8doWhile{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String text;
            do{
                text = scanner.nextLine();
                if (text.equalsIgnoreCase("done")) {
                    System.out.println("you have entered done ,i am stopping");
                    return;
                }
                if (text.charAt(0) == text.charAt(text.length()-1)) {
                    System.out.println("first and last character matched");
                }
                else {
                    System.out.println("first and last not matched");
                }
            } while (true);

        }
    }

