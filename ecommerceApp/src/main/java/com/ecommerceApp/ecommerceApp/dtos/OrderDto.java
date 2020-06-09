package com.ecommerceApp.ecommerceApp.dtos;

import com.ecommerceApp.ecommerceApp.entities.Address;
import com.ecommerceApp.ecommerceApp.entities.Cart;
import com.ecommerceApp.ecommerceApp.entities.Customer;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class OrderDto {
    private String id;
    private Integer amountPaid;
    private Date order_placed_date;
//   List<Customer> customerList;
//   private Customer customer;
    List<Cart>cartList;
    private String customerName;
    private String paymentMethod;
   private String customerAddressCity;
    private String customerAddressState;
    private String customerAddressCountry;
    private String customerAddressAddressLine;
    private Integer customerAddressZipCode;
    private String customerAddressLabel;

    public OrderDto(){

    }
    public OrderDto(String id, Integer amountPaid,String customerName,List<Cart>cartList, Date order_placed_date, String paymentMethod,
                    String customerAddressAddressLine, String customerAddressCity, String customerAddressState,
                    String customerAddressCountry, String customerAddressLabel, Integer customerAddressZipCode) {
     this.id = id;
        this.amountPaid=amountPaid;
        this.cartList=cartList;
        this.customerName=customerName;
        this.order_placed_date =order_placed_date;
        this.paymentMethod=paymentMethod;
        this.customerAddressAddressLine=customerAddressAddressLine;
        this.customerAddressCity=customerAddressCity;
        this.customerAddressState=customerAddressState;
        this.customerAddressCountry=customerAddressCountry;
        this.customerAddressLabel=customerAddressLabel;
        this.customerAddressZipCode=customerAddressZipCode;

    }

    public OrderDto(String id, List<Cart> cartList, String customerName, Integer amountPaid,
                    Date order_placed_date, String paymentMethod, String customerAddressAddressLine,
                    String customerAddressCity, String customerAddressState, String customerAddressCountry,
                    String customerAddressLabel, Integer customerAddressZipCode) {
        this.id = id;
        this.amountPaid=amountPaid;
        this.cartList=cartList;
        this.customerName=customerName;
        this.order_placed_date =order_placed_date;
        this.paymentMethod=paymentMethod;
        this.customerAddressAddressLine=customerAddressAddressLine;
        this.customerAddressCity=customerAddressCity;
        this.customerAddressState=customerAddressState;
        this.customerAddressCountry=customerAddressCountry;
        this.customerAddressLabel=customerAddressLabel;
        this.customerAddressZipCode=customerAddressZipCode;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }


    public Date getOrder_placed_date() {
        return order_placed_date;
    }

    public void setOrder_placed_date(Date order_placed_date) {
        this.order_placed_date = order_placed_date;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    //
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
//
    public String getCustomerAddressCity() {
        return customerAddressCity;
    }

    public void setCustomerAddressCity(String customerAddressCity) {
        this.customerAddressCity = customerAddressCity;
    }



    public String getCustomerAddressState() {
        return customerAddressState;
    }

    public void setCustomerAddressState(String customerAddressState) {
        this.customerAddressState = customerAddressState;
    }

//    public List<Customer> getCustomerList() {
//        return customerList;
//    }
//
//    public void setCustomerList(List<Customer> customerList) {
//        this.customerList = customerList;
//    }

    public String getCustomerAddressCountry() {
        return customerAddressCountry;
    }

    public void setCustomerAddressCountry(String customerAddressCountry) {
        this.customerAddressCountry = customerAddressCountry;
    }

    public String getCustomerAddressAddressLine() {
        return customerAddressAddressLine;
    }

    public void setCustomerAddressAddressLine(String customerAddressAddressLine) {
        this.customerAddressAddressLine = customerAddressAddressLine;
    }

    public String getCustomerAddressLabel() {
        return customerAddressLabel;
    }

    public void setCustomerAddressLabel(String customerAddressLabel) {
        this.customerAddressLabel = customerAddressLabel;
    }

    public Integer getCustomerAddressZipCode() {

        return customerAddressZipCode;
    }

    public void setCustomerAddressZipCode(Integer customerAddressZipCode) {
        this.customerAddressZipCode = customerAddressZipCode;
    }
}
