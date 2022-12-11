package com.mpautasso.homebanking.exceptions.custom;

public class EntityNotFoundException extends RuntimeException{
    private final static String DESCRIPTION = "Entity not found (400)";

    public EntityNotFoundException(String detail){
        super(DESCRIPTION + ". " + detail);
    }
}
