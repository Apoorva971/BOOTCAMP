/*Write the following a functional interface and implement it using lambda:

    (1) First number is greater than second number or not
      Parameter (int ,int ) Return boolean
    (2) Increment the number by 1 and return incremented value
      Parameter (int) Return int
    (3) Concatination of 2 string
     Parameter (String , String ) Return (String)
    (4) Convert a string to uppercase and return .
       Parameter (String) Return (String)
*/
package main.java.com.learning.day5;

public class Q1_ImplementLambda {
    public static void main(String[] args) {
        GreaterNumber greaterNumber = (a, b) ->
        {
            if (a > b) {
                return true;
            } else {
                return false;
            }
        };
        System.out.println(greaterNumber.checkgreater(5, 6));
        IncreasedValue increasedValue = (a) ->
        {
            a = a + 1;
            return (a);
        };
        System.out.println(increasedValue.increase(5));
        ConcatenateString concatenateString = (str1, str2) ->
        {
            String str = str1 + str2;
            return (str);
        };
        System.out.println(concatenateString.concatenate("apoorva", "garg"));
        StringToUppercase stringToUppercase = (str) -> {
            return (str.toUpperCase());
        };
        System.out.println(stringToUppercase.uppercase("apoorva"));
    }

}

//find whether a is greater or b
@FunctionalInterface
interface GreaterNumber {
    boolean checkgreater(int a, int b);
}

//will returned the updated value of a
@FunctionalInterface
interface IncreasedValue {
    int increase(int a);

}

//will concatenate both strings
@FunctionalInterface
interface ConcatenateString {
    String concatenate(String str1, String str2);
    //str1 is first string
    //str2 is second string
}

//will convert string to uppercase
@FunctionalInterface
interface StringToUppercase {
    String uppercase(String str);
}