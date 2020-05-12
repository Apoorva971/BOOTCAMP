package com.ecommerceApp.ecommerceApp.entities;

public class ReturnJson {
    private String message;

    public ReturnJson(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
