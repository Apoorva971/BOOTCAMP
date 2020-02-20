/* showing try, multi-catch and finally blocks.  */
package java2;
public class Q6 {
    public static void main(String[] args) {
        int a =0;
        int b =1;
        int c = b/a;
        //try block
        try{
            System.out.println(c);
        }
        //multi-catch
        catch (ArithmeticException e){
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //finally blocks
        finally {
            System.out.println("Must error");
        }
    }
}