package java2;

public class Q3 {
    public static void main(String args[]) {
        try
        {
            Class.forName("anyclass");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        Noclassdeffound def = new Noclassdeffound();
        def.show();
    }
}