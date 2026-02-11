package com.gatekeeper.Service.Exceptions;

public class PasswordMismtachException extends RuntimeException{

    public PasswordMismtachException(){
        super("Password and Confirmed Password Does not Match");
    }

}
