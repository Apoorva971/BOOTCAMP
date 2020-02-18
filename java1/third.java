import java.util.Scanner;

public class third {
public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    String str= sc.nextLine();
    String ch = sc.nextLine();
    //System.out.println("enter the character"+ch);
    if(ch.length()==1) {
        int count = str.length() - str.replace(ch, "").length();
        System.out.println("the occurance of " + ch + " in " + str + " is " + count);
    }
}
}
