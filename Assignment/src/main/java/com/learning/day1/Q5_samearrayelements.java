package main.java.com.learning.day1;

import java.util.Scanner;
public class Q5_samearrayelements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int a[] = new int[n];
        System.out.println("enter the size of array 1: n="+n);
        int m= sc.nextInt();
        int b[]=new int[m];
        System.out.println("enter the size of array 2: m="+m);
        System.out.println("enter first array");
        for (int i = 0; i < n; i++)
        {
            a[i] = sc.nextInt();
        }
        System.out.println("enter second array");
        for (int i = 0; i < m; i++)
        {
            b[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++){
               if(b[j]==a[i]){
                   System.out.println(a[i]);
                   b[j]='0';
                   a[i]='0';
               }
        }
        }
    }
}
