//Calculate the number & Percentage Of Lowercase Letters,Uppercase Letters, Digits And Other Special Characters In A String

import java.util.Scanner;

public class fourth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int upperCaseLetters=0 , digit=0, others=0;
        int lowerCaseLetters=0;
        int strlen = str.length();
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch))
            {
                upperCaseLetters++;
            }

            else if(Character.isLowerCase(ch))
            {
                lowerCaseLetters++;
            }

            else if (Character.isDigit(ch))
            {
                digit++;
            }
            else
            {
                others++;
            }
        }
        System.out.println("upperCaseLetters = "+upperCaseLetters);
        System.out.println("lowerCaseLetters = "+lowerCaseLetters);
        System.out.println("digit = "+digit);
        System.out.println("others = "+others);
        double upPercentage = (upperCaseLetters * 100.0) / strlen ;
        double loPercentage = (lowerCaseLetters * 100.0) / strlen;
        double diPercentage = (digit * 100.0) / strlen;
        double oPercentage = (others * 100.0) / strlen;
        System.out.println("upPercentage = "+upPercentage);
        System.out.println("loPercentage = "+loPercentage);
        System.out.println("diPercentage = "+diPercentage);
        System.out.println("oPercentage = "+oPercentage);
    }

}
