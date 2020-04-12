package com.ecommerceApp.ecommerceApp.dtos;

public class CustomerRegistrationDto extends UserRegistrationDto {

    //  @NotNull
    //@NotEmpty
    //@Size(min = 10, max = 10)
    private String contact;


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}