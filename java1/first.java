// Write a program to replace a substring inside a string with other string ?

import java.util.Scanner;

public class first {
    public static void main(String args[]){
      //  String s="how are you?";
        //        System.out.println(s.replaceAll("ow are","me min"));
   Scanner sc = new Scanner(System.in);
   String s=sc.nextLine();
   String s1=sc.nextLine();
   int firstindex = sc.nextInt();
   int lastindex = sc.nextInt();
   System.out.println("enter starting index of"+firstindex);
   System.out.println("enter last index"+lastindex);
   String substr= s.substring(firstindex,lastindex);
   System.out.println("the substring is "+substr);
   String newsub = sc.next();
   System.out.println(s.replaceAll(substr,newsub));

    }
}
