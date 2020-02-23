package main.java.com.learning.day2;

public class Q13 {
        public static void main(String[] args) {
            try {
                throw new CustomException("custom exception");
            } catch (CustomException e){
                System.out.println(e.getMessage());
            }
        }
    }

    class CustomException extends Exception{
        CustomException(String message){
            super(message);
        }
    }
