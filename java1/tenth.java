//Write a single program for following operation using overloading
 // A) Adding 2 integer number
 // B) Adding 2 double
 //C) multiplying 2 float
 //D) multiplying 2 int
 //E) concate 2 string
 //F) Concate 3 String

import java.util.Scanner;

public class tenth {
    public static int overload(int x,int y)
    {
        System.out.println(x+y);
        return x+y;
    }
    public static double overload(double x,double y)
    {
        System.out.println(x+y);
        return x+y;
    }
    public static float overload(float x,float y)
    {
        System.out.println(x*y);
        return x*y;
    }
    public static void overload()
    {
        int x,y;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter elements:");
        x=sc.nextInt();
        y=sc.nextInt();
        System.out.println(x*y);
    }
    public static String overload(String a,String b)
    {
        System.out.println(a+b);
        return a+b;
    }
    public static String overload(String a,String b,String c)
    {
        System.out.println(a+b+c);
        return a+b+c;
    }
    public static void main(String[] agrs) {
        int ch;

        System.out.println("1.Adding Two Integers");
        System.out.println("2.Adding Two Doubles");
        System.out.println("3.Multiplying Two Floats");
        System.out.println("4.Multiply two int");
        System.out.println("5.Concate 2 Strings");
        System.out.println("6.Concate 3 strings");
        System.out.println("Enter your Choice");
        Scanner sc=new Scanner(System.in);
        ch=sc.nextInt();
        switch (ch) {
            case 1:
                int x = 0, y = 0,z=0;
                System.out.println("Enter Two Values");
                Scanner s1 = new Scanner(System.in);
                x = s1.nextInt();
                y = s1.nextInt();
                overload(x,y);
                break;
            case 2:
                double a,b;
                System.out.println("Enter Two Values");
                Scanner s2 = new Scanner(System.in);
                a = s2.nextDouble();
                b = s2.nextDouble();
                overload(a,b);
                break;
            case 3:
                float c,d;
                System.out.println("Enter Two Values");
                Scanner s3 = new Scanner(System.in);
                c = s3.nextFloat();
                d = s3.nextFloat();
                overload(c,d);
                break;
            case 4:
                overload();
                break;
            case 5:
                String str1,str2;
                Scanner s5=new Scanner(System.in);
                System.out.println("Enter Two String");
                str1=s5.nextLine();
                str2=s5.nextLine();
                overload(str1,str2);
                break;
            case 6:
                String str3,str4,str5;
              Scanner s6=new Scanner(System.in);
                System.out.println("Enter Three String");
                str3=s6.nextLine();
                str4=s6.nextLine();
                str5=s6.nextLine();
                overload(str3,str4,str5);
                break;
            default:
                System.out.println("Wrong Choice");
        }
    }
}
