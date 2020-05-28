package com.ecommerceApp.ecommerceApp.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
@Document(collection = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Integer amountPaid;
    @Temporal(TemporalType.DATE)
    private Date date_created;

    private String paymentMethod;
    private String customerAddressCity;
    private String customerAddressState;
    private String customerAddressCountry;
    private String customerAddressAddressLine;
    private Integer customerAddressZipCode;
    private String customerAddressLabel;


//    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
//    private List<OrderProduct> orderProductsList;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customer_user_id")
//    private Customer customer;

    public Orders(String id, Integer amountPaid, Date date_created, String paymentMethod, String customerAddressCity,
                  String customerAddressState, String customerAddressCountry,
                  String customerAddressAddressLine, Integer customerAddressZipCode, String customerAddressLabel) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.date_created = date_created;
        this.paymentMethod = paymentMethod;
        this.customerAddressCity = customerAddressCity;
        this.customerAddressState = customerAddressState;
        this.customerAddressCountry = customerAddressCountry;
        this.customerAddressAddressLine = customerAddressAddressLine;
        this.customerAddressZipCode = customerAddressZipCode;
        this.customerAddressLabel = customerAddressLabel;
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

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

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

    public Integer getCustomerAddressZipCode() {
        return customerAddressZipCode;
    }

    public void setCustomerAddressZipCode(Integer customerAddressZipCode) {
        this.customerAddressZipCode = customerAddressZipCode;
    }

    public String getCustomerAddressLabel() {
        return customerAddressLabel;
    }

    public void setCustomerAddressLabel(String customerAddressLabel) {
        this.customerAddressLabel = customerAddressLabel;
    }

//    public List<OrderProduct> getOrderProductsList() {
//        return orderProductsList;
//    }
//
//    public void setOrderProductsList(List<OrderProduct> orderProductsList) {
//        this.orderProductsList = orderProductsList;
//    }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
}