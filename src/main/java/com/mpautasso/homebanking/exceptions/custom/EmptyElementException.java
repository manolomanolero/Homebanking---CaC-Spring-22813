package com.mpautasso.homebanking.exceptions.custom;

public class EmptyElementException extends RuntimeException{
    private final static String DESCRIPTION = "Empty element (400)";

    public EmptyElementException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
