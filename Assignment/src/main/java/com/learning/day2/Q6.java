package main.java.com.learning.day2;

public class Q6 {
    public static void main(String[] args) {
        int a =0;
        int b =1;
        int c = b/a;
        try{
            System.out.println(c);
        }
        catch (ArithmeticException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Must error");
        }
    }
}