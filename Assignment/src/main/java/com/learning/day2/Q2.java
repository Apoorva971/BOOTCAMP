package java2;
import java.util.Scanner;
public class Q2 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("enter the string to be sort: ");
            String str = sc.nextLine();
            //System.out.println("Before Sorting:" + str);
            int j = 0;
            char temp = 0;
            char[] ch = str.toCharArray();
            for(int i=0; i < ch.length; i++) {
                for(j=0; j < ch.length; j++) {
                    if(ch[j] > ch[i]) {
                        temp = ch[i];
                        ch[i] = ch[j];
                        ch[j] = temp;
                    }
                }
            }
            System.out.println("string after sorting:");
            for(int i=0; i < ch.length; i++) {
                System.out.print(ch[i]);
            }
        }
    }
