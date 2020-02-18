import java.util.Scanner;

public class eight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str =sc.nextLine();
        StringBuffer sb =new StringBuffer(str);
        System.out.println(sb.reverse());
        System.out.println(sb.delete(4,9));
    }
}