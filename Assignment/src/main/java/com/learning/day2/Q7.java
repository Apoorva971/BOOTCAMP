/*    converting seconds into days, hours, minutes and seconds.    */
package main.java.com.learning.day2;
import java.util.Scanner;
public class Q7
{
    /*
Number of days = ⌊ n / (24 * 3600) ⌋
Number of Hours = ⌊ (n % (24 * 3600)) / 3600 ⌋
Number of Minutes = ⌊ (n % (24 * 3600 * 3600)) / 60 ⌋
Number of Seconds = ⌊ (n % (24 * 3600 * 3600 * 60)) / 60 ⌋
*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the seconds");
        int n = sc.nextInt();
        double day = n / (24 * 3600);
        System.out.println("no of days is :- "+ day);
        n = n % (24 * 3600);
        double hour = n / 3600;
        System.out.println("no of hour is :- "+ hour);
        n %= 3600;
        double minutes = n / 60;
        System.out.println("no of minutes is :- "+ minutes);
        n %= 60;
        double seconds = n;
        System.out.println("no of seconds is :- "+ seconds);
    }
}
