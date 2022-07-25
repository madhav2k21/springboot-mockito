package com.techleads.app.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
