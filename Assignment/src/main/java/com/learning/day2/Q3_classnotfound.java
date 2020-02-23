package main.java.com.learning.day2;

public class Q3_classnotfound {
        public static void main(String[] args) {
            try{
                Class.forName("ankit.class");
            }
            catch (Exception e){
                System.out.println(e+" class not found");
            }
        }
    }
