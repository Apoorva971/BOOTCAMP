package main.java.com.learning.day1;

enum House {
        Castle(900),Apartment(2),Bungalow(50),TinyHome(15),MultiFamily(12);
        private int price;
        House(int p) {
            price = p;
        }
        int getPrice() {
            return price;
        }
    }
    public class Q9_enum {
        public static void main(String args[]){
            System.out.println("All House prices:");
            for (House h : House.values())
                System.out.println(h + " costs " + h.getPrice() + " Thousand Euros");
        }
    }

