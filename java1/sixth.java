// There is an array with every element repeated twice except one. Find that element

import java.util.Scanner;
public class sixth {
    public static void main(String[] args) {
        int n;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of elements you want in array:");
        n = s.nextInt();
        int a[] = new int[n];
        System.out.println("Enter "+n+" Elements:");
        for(int i = 0; i < n; i++)
        {
            a[i] = s.nextInt();
        }
        for(int i=0;i<n;i++)
        { int count=0;
            for(int j=0;j<n;j++)
            {
                if(a[i]==a[j])
                {
                    count++;
                }

            }
            if ((count==1))
            {
                System.out.println("Output:" +a[i]);
                break;

            }
        }
    }
}
