package com.springboot.shoppingapp.exception;

public class CartServiceException extends RuntimeException {

    public CartServiceException(String message) {
        super(message);
    }
}
