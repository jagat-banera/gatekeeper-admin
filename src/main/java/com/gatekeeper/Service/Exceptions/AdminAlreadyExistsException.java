package com.gatekeeper.Service.Exceptions;

public class AdminAlreadyExistsException extends RuntimeException{

    public AdminAlreadyExistsException(){
        super("Admin User Already Exists");
    }
}
