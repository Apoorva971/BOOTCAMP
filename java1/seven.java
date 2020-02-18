import java.util.Scanner;

public class seven {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your first name");
        String Firstname = sc.nextLine();
        System.out.println("enter your last name");
        String Lastname = sc.nextLine();
        staticd sd = new staticd();
        sd.showdata(Firstname,Lastname);

    }
}

    class staticd {
        static int age;
        static String Firstname = "";
        static String Lastname = "";

        static {
            age = 20;
        }

        static void showdata(String Firstn, String Lastn) {
            Firstname = Firstn;
            Lastname = Lastn;
            System.out.println("your first name is " + Firstname);
            System.out.println("your last name is " + Lastname);
            System.out.println("your age is " + age);

        }
    }
