/* Design classes having attributes for furniture where there are wooden chairs
 and tables, metal chairs and tables.
  There are stress and fire tests for each products.   */
package java2;

import java.util.Scanner;
abstract class Furniture{
    abstract void stress();
    abstract void fire();
    abstract void showdetails();
}
class WoodenChairs extends Furniture {
    String color ="Brown";
    char size='S';
    public void stress(){
        System.out.println("Stress");
        System.out.println(color);
    }
    public void fire(){
        System.out.println("Fire");
    }
    public void showdetails(){
        System.out.println("color of chair: "+color+" size of chair"+size);
    }

}
class Tables extends Furniture{
    String color ="Golden Brown";
    char size='L';
    public void stress(){
        System.out.println("Stress");
    }
    public void fire(){
        System.out.println("Fire");
    }
    public void showdetails(){
        System.out.println("color of chair: "+color+" size of chair"+size);
    }
}
class MetalChairs extends Furniture{
    String color ="Metal Brown";
    char size='M';
    public void stress(){
        System.out.println("Stress");
    }
    public void fire(){
        System.out.println("Fire");
    }
    public void showdetails(){
        System.out.println("color of chair: "+color+" size of chair"+size);
    }
}
class Q9 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter type of furniture ie. WoodenChairs, Tables, MetalChairs");
        String type = sc.next();
        Furniture d = null;
        if(type.equals("WoodenChairs")){
            d = new WoodenChairs();
        }
        else if(type.equals("Tables"))
        {
            d = new Tables();
        }
        else if(type.equals("MetalChairs"))

        {
            d = new MetalChairs();
        }
        d.fire();
        d.stress();
        d.showdetails();
    }
}
