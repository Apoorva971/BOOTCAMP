package main.java.com.learning.day2;

public class Q9_furniture {
        public static void main(String[] args) {
            Wood wood = new Wood();
            wood.fireTest();
            wood.stressTest();
            Metal metal = new Metal();
            metal.fireTest();
            metal.fireTest();
        }
}
abstract class Furniture {
    String type="";
    Furniture(){ }
    abstract public void stressTest();
    abstract public void fireTest();
}
class Wood extends Furniture{

    Wood() {
        System.out.println("Wooden Chairs and Tables with properties:");
    }
    public void stressTest(){
        System.out.println("Fails stress test");
    }
    public void fireTest(){
        System.out.println("Fails fire test");
    }
}
class Metal extends Furniture{

    Metal(){
        System.out.println("Metal Chairs and Tables with properties:");
    }
    public void stressTest(){
        System.out.println("Passes stress test");
    }
    public void fireTest(){
        System.out.println("Passes fire test");
    }
}
