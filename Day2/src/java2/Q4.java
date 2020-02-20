/* This program about to create singleton class.*/

package java2;
class singleton {
    public static singleton single_instance = null;
    private singleton()
    {
        System.out.println("singleton class constructor called");
    }
        public static singleton getInstance()
        {
            if (single_instance == null)
                single_instance = new singleton();

            return single_instance;
        }

}
class Q4{
    public static void main(String args[]) {
        singleton obj1 = singleton.getInstance();
        singleton obj2 = singleton.getInstance();
        System.out.println(obj1);
        System.out.println(obj2);

    }
}


