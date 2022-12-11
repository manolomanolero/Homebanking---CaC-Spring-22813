package com.mpautasso.homebanking.exceptions.custom;

public class InvalidElementException extends RuntimeException{
    private final static String DESCRIPTION = "Invalid element (400)";

    public InvalidElementException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
