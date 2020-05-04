package com.ecommerceApp.ecommerceApp.exceptions;

public class InvalidCategoryOrFieldIdException extends RuntimeException {
    public InvalidCategoryOrFieldIdException(String message){
        super(message);
    }
}
