package com.ecommerceApp.ecommerceApp.dtos;
import java.util.Date;

public class OrderDto {
    private String id;
    private Integer amountPaid;
    private Date dateCreated;
    private String paymentMethod;
    private String customerAddressCity;
    private String customerAddressState;
    private String customerAddressCountry;
    private String customerAddressAddressLine;
    private Integer customerAddressZipCode;
    private String customerAddressLabel;

    public OrderDto(){

    }
    public OrderDto(String id, Integer amountPaid, Date date_created, String paymentMethod,
                    String customerAddressAddressLine, String customerAddressCity, String customerAddressState,
                    String customerAddressCountry, String customerAddressLabel, Integer customerAddressZipCode) {
     this.id = id;
        this.amountPaid=amountPaid;
        this.dateCreated=date_created;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
