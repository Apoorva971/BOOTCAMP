package main.java.com.learning.day2;
public class Q10_skeleton {
    public static void main(String[] args) {
        Cashier cashier = new Cashier();
        Bistro bistro = new Bistro();
        Customer customer = new Customer("Apoorva",4002);
        customer.PlaceOrder();
        cashier.takeOrder(customer.getName());
        int token=cashier.giveTokenNo(customer.getName());
        customer.getTokeno(token);
        cashier.receivePayment(customer.getTokeno(token));
        cashier.addItToPendingQueue(customer.getTokeno(token));
        customer.checkOrderStatus(customer.getTokeno(token));
        bistro.getOrderFromPendingQueue();
        bistro.prepareOrder();
        bistro.insertOrderIntoCompletedOrderQueue();
    }
}
class Customer
{
    private String name;
    private double phone;
    private int tokeno;
    Customer(String name,double phone)
    {
        this.name=name;
        this.phone=phone;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPhone() {
        return phone;
    }

    public void setPhone(double phone) {
        this.phone = phone;
    }

    public int getTokeno(int t) {
        tokeno=t;
        return tokeno;
    }

    public void setTokeno(int tokeno) {
        this.tokeno = tokeno;
    }
    public void PlaceOrder()
    {

        //customer places order on the basis of his name and phone number
    }
    public void checkOrderStatus(int tokeno)
    {

        // customer checks order status on the basis of tokeno given him

    }
    public void collectOrder(int tokeno)
    {
        // customer can collect the coffee after entering tokenno
    }
    public void payment()
    {
        //payment is done through this method
    }
    public void wrongOrder() {
        //not liking his coffee, he getting wrong coffee are not considered to keep the design simple.

    }   }
class Cashier
{
    String cashierName;
    int CashierId;


    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public int getCashierId() {
        return CashierId;
    }

    public void setCashierId(int cashierId) {
        CashierId = cashierId;
    }

    public void takeOrder(String customerName)
    {
        //cashier receives the order
    }
    public int giveTokenNo(String customerName)
    {
        int token=0;
        //cashier provides tokeno to the customer
        return token;
    }
    public void receivePayment(int tokenno)
    {
        // receives payment
    }
    public void addItToPendingQueue(int tokeno)
    {
        //called when there is a queue of orders
    }
}
class Bistro {
    public void getOrderFromPendingQueue() {
        //take order from pending queue
    }

    public void prepareOrder() {
        // called when order is preparing
    }

    public void insertOrderIntoCompletedOrderQueue() {
        //output to completed order queue
    }
}

