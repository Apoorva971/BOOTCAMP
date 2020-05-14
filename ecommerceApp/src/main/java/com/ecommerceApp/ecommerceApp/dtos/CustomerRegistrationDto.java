package com.ecommerceApp.ecommerceApp.dtos;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerRegistrationDto extends UserRegistrationDto {

    @Pattern(regexp = "^[789]\\d{9}$", message = "Please enter a valid number!")
    @Size(min = 10, max = 10, message = "Contact Number should be of length 10")
    @NotNull
    private String contact;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}